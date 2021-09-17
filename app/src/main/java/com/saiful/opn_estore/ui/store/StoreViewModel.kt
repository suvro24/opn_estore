package com.saiful.opn_estore.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.opn_estore.utils.Failure
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.repository.Repository
import com.saiful.opn_estore.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _storeInfo: MutableLiveData<Store> = MutableLiveData()
    val storeInfo: LiveData<Store> = _storeInfo

    private val _productList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _cartList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    private val cartList: LiveData<List<Product>> = _cartList

    private val _errorStoreEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val errorStoreEvent: LiveData<Event<Unit>> = _errorStoreEvent

    private val _errorProductEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val errorProductEvent: LiveData<Event<Unit>> = _errorProductEvent


    fun fetchStoreAndProduct() {
        _isLoading.value = true
        if (storeInfo.value != null && productList.value != null) {
            viewModelScope.launch {
                _cartList.value = repository.getAllCart()
                println(_cartList.value?.size)
                _productList.value = combineProductData(productList.value!!).toList()
                _isLoading.value = false
            }
            return
        }
        viewModelScope.launch {
            try {
                _cartList.value = repository.getAllCart()
                repository.getStores().successOrError(::onFetchStoreSuccess, ::onFetchStoreFailed)
                repository.getProducts()
                    .successOrError(::onFetchProductSuccess, ::onFetchProductFailed)
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                onFetchStoreFailed(Failure.ParsingError)
                onFetchProductFailed(Failure.ParsingError)
            }

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
        _storeInfo.value =store
        //setStore(store)
    }

    private fun onFetchStoreFailed(failure: Failure) {
        _errorStoreEvent.value = Event(Unit)
    }

    private fun onFetchProductSuccess(items: List<Product>) {
        _productList.value = combineProductData(items)

    }

    private fun onFetchProductFailed(failure: Failure) {
        _errorProductEvent.value = Event(Unit)
    }

    private fun combineProductData(items: List<Product>): List<Product> {
        if (_cartList.value!!.isEmpty()) {
            items.forEach { it ->
                it.updateQty(0)
            }
        } else {
            _cartList.value?.forEach { cartItem ->
                items.find { it.name == cartItem.name }?.updateQty(cartItem.qty)
            }
        }
        return items
    }

    fun setProducts(products: List<Product>){
        _productList.value = products.toList()
    }

}