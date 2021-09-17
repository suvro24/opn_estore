package com.saiful.opn_estore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.ui.order_summary.OrderSummaryViewModel
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
class OrderSummaryScreenViewModelTest {

    private lateinit var viewModel: OrderSummaryViewModel

    private lateinit var fakeRepository: FakeRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        fakeRepository = FakeRepository()
        viewModel = OrderSummaryViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchCartList() {
        runBlocking {
            viewModel.fetchCart()
        }
        val bool = viewModel.cartProductList.getOrAwaitValue().isNotEmpty()

        Assert.assertTrue(bool)
    }

    @Test
    fun checkTotalPrice() {
        runBlocking {
            viewModel.fetchCart()
        }
        var totalSum = 0
        viewModel.cartProductList.getOrAwaitValue().forEach {
            totalSum += (it.price * it.qty)
        }
        Assert.assertEquals(viewModel.totalPrice.getOrAwaitValue(), totalSum)
    }

    @Test
    fun checkIfAddressChanged() {
        val add = "Road 1, Bangkok, Thailand"
        viewModel.onAddressTextChanged(add)

        Assert.assertEquals(viewModel.address.getOrAwaitValue(), add)
    }
    @Test
    fun checkIfAddressEmpty() {
        viewModel.onAddressTextChanged("     ")
        Assert.assertTrue(viewModel.address.getOrAwaitValue().isBlank())
    }

    @Test
    fun checkErrorEventIfAddressEmpty() {
        viewModel.onAddressTextChanged("     ")
        viewModel.confirmOrder()
        Assert.assertNotNull(viewModel.addressErrorEvent.getOrAwaitValue())
    }

    @Test
    fun checkConfirmOrderNavigationEvent() {
        viewModel.onAddressTextChanged("Road 1, Bangkok, Thailand")
        viewModel.confirmOrder()
        Assert.assertNotNull(viewModel.navigationEvent.getOrAwaitValue())
    }
}