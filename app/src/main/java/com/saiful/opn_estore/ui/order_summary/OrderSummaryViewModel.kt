package com.saiful.opn_estore.ui.order_summary

import androidx.lifecycle.*
import com.saiful.opn_estore.R
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSummaryViewModel @Inject constructor(repository: DefaultRepository) :
    ViewModel() {

    val cartProductList: LiveData<List<Product>> = repository.getAllProductFromCart().asLiveData()

    val totalPrice: LiveData<Int> = cartProductList.switchMap {
        var totalSum = 0;
        it.forEach { item ->
            totalSum += (item.price * item.qty)
        }
        liveData { emit(totalSum) }
    }

    private val _addressErrorEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val addressErrorEvent: LiveData<Event<Unit>> = _addressErrorEvent

    private val _address: MutableLiveData<String> = MutableLiveData()
    val address: LiveData<String> = _address

    private val _navigationEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val navigationEvent: LiveData<Event<Unit>> = _navigationEvent


    fun placeOrder() {
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