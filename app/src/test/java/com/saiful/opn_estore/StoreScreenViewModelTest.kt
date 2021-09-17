package com.saiful.opn_estore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.ui.store.StoreViewModel
import com.saiful.opn_estore.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*


@ExperimentalCoroutinesApi
class StoreScreenViewModelTest {


    private lateinit var storeViewModel: StoreViewModel
    private lateinit var fakeRepository: FakeRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {

        Dispatchers.setMain(TestCoroutineDispatcher())
        fakeRepository = FakeRepository()
        fakeRepository.fakeAPI.setSuccessResponse = true
        storeViewModel = StoreViewModel(fakeRepository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun checkFetchStoreProductsSuccess() {
        fakeRepository.fakeAPI.setSuccessResponse = true
        runBlocking {
            storeViewModel.fetchStoreAndProduct()
        }
        Assert.assertNotNull(storeViewModel.storeInfo.getOrAwaitValue())
        Assert.assertTrue(storeViewModel.productList.getOrAwaitValue().isNotEmpty())

    }

    @Test
    fun checkIfFetchProductsFailed() {
        fakeRepository.fakeAPI.setSuccessResponse = false
        runBlocking {
            storeViewModel.fetchStoreAndProduct()
        }
        Assert.assertNotNull(storeViewModel.errorProductEvent.getOrAwaitValue())

    }

    @Test
    fun checkIfFetchStoreFailed() {

        fakeRepository.fakeAPI.setSuccessResponse = false
        runBlocking {
            storeViewModel.fetchStoreAndProduct()
        }
        Assert.assertNotNull(storeViewModel.errorStoreEvent.getOrAwaitValue())

    }

    @Test
    fun storeIsNull() {
        fakeRepository.fakeAPI.setSuccessResponse = false
        val bool = try {
            runBlocking {
                storeViewModel.fetchStoreAndProduct()
            }
            storeViewModel.storeInfo.getOrAwaitValue()
            false
        } catch (e: Exception) {
            true
        }
        Assert.assertTrue(bool)

    }

    @Test
    fun storeIsNotNull() {
        fakeRepository.fakeAPI.setSuccessResponse = true
        val bool = try {
            runBlocking {
                storeViewModel.fetchStoreAndProduct()
            }
            storeViewModel.storeInfo.getOrAwaitValue()
            true
        } catch (e: Exception) {
            false
        }
        Assert.assertTrue(bool)

    }


    @Test
    fun storeExpectedValue() {
        fakeRepository.fakeAPI.setSuccessResponse = true
        runBlocking {
            storeViewModel.fetchStoreAndProduct()
        }

        Assert.assertNotEquals(storeViewModel.storeInfo.getOrAwaitValue().name, "Coffee Store")
        Assert.assertEquals(storeViewModel.storeInfo.getOrAwaitValue().name, "Tea shop")

    }

    @Test
    fun productListIsEmpty() {
        storeViewModel.setProducts(emptyList())
        val bool = storeViewModel.productList.getOrAwaitValue().isEmpty()

        Assert.assertTrue(bool)

    }

    @Test
    fun productListHasItem(){
        runBlocking {
            fakeRepository.getProducts().successOrError({ storeViewModel.setProducts(it)
            }, {})
        }
        val bool = storeViewModel.productList.getOrAwaitValue().isNotEmpty()

        Assert.assertTrue(bool)
    }

    @Test
    fun addProductQuantity(){
        runBlocking {

            fakeRepository.getProducts().successOrError({ storeViewModel.setProducts(it)
            }, {})
            storeViewModel.addItem(Product("Product 1"))
        }
        val product = storeViewModel.productList.getOrAwaitValue().find { it.name == "Product 1" }
        Assert.assertEquals(product?.qty, 1)
    }

    @Test
    fun removeProductQuantity(){
        runBlocking {
            fakeRepository.getProducts().successOrError({ storeViewModel.setProducts(it)
            }, {})
            storeViewModel.addItem(Product("Product 1"))
            storeViewModel.addItem(Product("Product 1"))
            storeViewModel.addItem(Product("Product 1"))


            storeViewModel.removeItem(Product("Product 1"))
        }
        val product = storeViewModel.productList.getOrAwaitValue().find { it.name == "Product 1" }
        Assert.assertEquals(product?.qty, 2)
    }
}