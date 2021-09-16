package com.saiful.opn_estore.ui.order_summary

import androidx.lifecycle.*
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.model.Product
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
}