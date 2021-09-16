package com.saiful.opn_estore.ui.store

import android.util.Log
import androidx.lifecycle.*
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _storeInfo: MutableLiveData<Store> = MutableLiveData()
    val storeInfo: LiveData<Store> = _storeInfo

    private val _productList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _cartList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    private val cartList: LiveData<List<Product>> = _cartList


    fun fetchStoreAndProduct() {
        _isLoading.value = true
        if (storeInfo.value != null && productList.value != null) {
            viewModelScope.launch {
                _cartList.value = repository.getAllCart()
                _productList.value = combineProductData(productList.value!!).toList()
                _isLoading.value = false
            }
            return
        }
        viewModelScope.launch {
            _cartList.value = repository.getAllCart()
            repository.getStores().successOrError(::onFetchStoreSuccess, ::onFetchStoreFailed)
            repository.getProducts()
                .successOrError(::onFetchProductSuccess, ::onFetchStoreFailed)
            _isLoading.value = false
        }
    }

    fun addItem(item: Product) {
        _productList.value?.find { it.name == item.name }?.addQty()
        _productList.value = _productList.value?.toList()
        viewModelScope.launch {
            repository.updateProduct(item)
        }


    }

    fun removeItem(item: Product) {
        _productList.value?.find { it.name == item.name }?.removeQty()
        _productList.value = _productList.value?.toList()

        viewModelScope.launch {
            repository.updateProduct(item)
        }
    }

    private fun onFetchStoreSuccess(store: Store) {
        _storeInfo.value = store
    }

    private fun onFetchStoreFailed(failure: Failure) {
        //TODO

    }

    private fun onFetchProductSuccess(items: List<Product>) {
//        _cartList.value?.forEach { cartItem ->
//            items.find { it.name == cartItem.name }?.updateQty(cartItem.qty)
//        }
        _productList.value = combineProductData(items)

    }

    private fun onFetchProductFailed(failure: Failure) {
        //TODO

    }

    private fun combineProductData(items: List<Product>): List<Product> {
        _cartList.value?.forEach { cartItem ->
            items.find { it.name == cartItem.name }?.updateQty(cartItem.qty)
        }
        return items
    }

}