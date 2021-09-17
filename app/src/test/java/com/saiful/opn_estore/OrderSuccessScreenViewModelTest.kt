package com.saiful.opn_estore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saiful.opn_estore.ui.order_success.OrderSuccessViewModel
import com.saiful.opn_estore.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*


@ExperimentalCoroutinesApi
class OrderSuccessScreenViewModelTest {

    private lateinit var viewModel: OrderSuccessViewModel

    private lateinit var fakeRepository: FakeRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        fakeRepository = FakeRepository()
        viewModel = OrderSuccessViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun checkIfOrderSuccessful(){
        fakeRepository.fakeAPI.setSuccessResponse = true
        val address= "Road 1, Bangkok, Thailand"
        runBlocking {
            viewModel.placeOrder(address)
        }
        Assert.assertNotNull(viewModel.successEvent.getOrAwaitValue())
    }

    @Test
    fun checkIfOrderFailed(){
        fakeRepository.fakeAPI.setSuccessResponse = false
        val address= "Road 1, Bangkok, Thailand"
        runBlocking {
            viewModel.placeOrder(address)
        }
        Assert.assertNotNull(viewModel.errorEvent.getOrAwaitValue())
    }

}