package com.saiful.opn_estore.ui.order_success

import androidx.lifecycle.*
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSuccessViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading


//    private val _cartProductList: MutableLiveData<List<Product>> = MutableLiveData()
//    val cartProductList: LiveData<List<Product>> = _cartProductList


    private val _errorEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val errorEvent: LiveData<Event<Unit>> = _errorEvent

    private val _successEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val successEvent: LiveData<Event<Unit>> = _successEvent

    fun placeOrder(address: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val cartList =  repository.getAllCart()
            repository.submitOrder(OrderRequestBody(cartList, address))
                .successOrError(::onPlaceOrderSuccess, ::onPlaceOrderFailed)
        }
    }

    private fun onPlaceOrderSuccess(unit: Unit) {
        viewModelScope.launch {
            repository.clearAllProductFromCart()
            isLoading.value = false
            _successEvent.value = Event(Unit)
        }
    }

    private fun onPlaceOrderFailed(failure: Failure) {
        _isLoading.value = false
        _errorEvent.value = Event(Unit)
    }
}