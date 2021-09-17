package com.saiful.opn_estore.ui.order_success

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.opn_estore.utils.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.repository.Repository
import com.saiful.opn_estore.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSuccessViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val errorEvent: LiveData<Event<Unit>> = _errorEvent

    private val _successEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val successEvent: LiveData<Event<Unit>> = _successEvent

    fun placeOrder(address: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val cartList = repository.getAllCart()
                repository.submitOrder(OrderRequestBody(cartList, address))
                    .successOrError(::onPlaceOrderSuccess, ::onPlaceOrderFailed)
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                onPlaceOrderFailed(Failure.ParsingError)

            }
        }
    }

    private fun onPlaceOrderSuccess(unit: Unit) {
        viewModelScope.launch {
            repository.clearAllProductFromCart()
            _successEvent.value = Event(Unit)
        }
    }

    private fun onPlaceOrderFailed(failure: Failure) {
        _errorEvent.value = Event(Unit)
    }
}