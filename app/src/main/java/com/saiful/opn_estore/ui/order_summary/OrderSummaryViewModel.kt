package com.saiful.opn_estore.ui.order_summary

import androidx.lifecycle.*
import com.saiful.opn_estore.R
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSummaryViewModel @Inject constructor(private val repository: DefaultRepository) :
    ViewModel() {

    private val _cartProductList: MutableLiveData<List<Product>> = MutableLiveData()
    val cartProductList: LiveData<List<Product>> = _cartProductList


    private val _totalPrice: MutableLiveData<Int> = MutableLiveData()
    val totalPrice: LiveData<Int> = _totalPrice


    private val _addressErrorEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val addressErrorEvent: LiveData<Event<Unit>> = _addressErrorEvent

    private val _address: MutableLiveData<String> = MutableLiveData("")
    val address: LiveData<String> = _address

    private val _navigationEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val navigationEvent: LiveData<Event<Unit>> = _navigationEvent

    fun fetchCart() {
        viewModelScope.launch {
            _cartProductList.value = repository.getAllCart()
            var totalSum = 0
            _cartProductList.value?.forEach {
                totalSum += (it.price * it.qty)
            }
            _totalPrice.value = totalSum
        }

    }

    fun confirmOrder() {
        if (_address.value.isNullOrBlank()) {
            _addressErrorEvent.value = Event(Unit)
        } else {
            _navigationEvent.value = Event(Unit)
        }
    }

    fun onAddressTextChanged(text: CharSequence) {
        _address.value = text.toString()
    }
}