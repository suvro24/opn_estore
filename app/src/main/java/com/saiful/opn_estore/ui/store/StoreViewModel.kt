package com.saiful.opn_estore.ui.store

import androidx.lifecycle.*
import com.saiful.opn_estore.data.DefaultRepository
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.ParentListItemModel
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    private val _storeInfo: MutableLiveData<Store> = MutableLiveData()
    //private val storeInfo: LiveData<Store> = _storeInfo

    private val _productList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    //private val productList: LiveData<List<Product>> = _productList


    private val _parentListItems: MediatorLiveData<List<ParentListItemModel>> =
        MediatorLiveData<List<ParentListItemModel>>()
    val parentListItems: LiveData<List<ParentListItemModel>> = _parentListItems


    init {
        _parentListItems.addSource(_storeInfo) {
            _parentListItems.value = combineStoreProductData(_storeInfo.value, _productList.value)
        }

        _parentListItems.addSource(_productList) {
            _parentListItems.value = combineStoreProductData(_storeInfo.value, _productList.value)
        }

    }

    fun fetchStoreAndProduct() {
        viewModelScope.launch {
            repository.getStores().successOrError(::onFetchStoreSuccess, ::onFetchStoreFailed)
            repository.getProducts()
                .successOrError(::onFetchProductSuccess, ::onFetchStoreFailed)

        }
    }

    fun addItem(item: Product) {
        _productList.value?.find { it.name == item.name }?.addQty()
        _productList.value = _productList.value?.toList()
        viewModelScope.launch {
            repository.addProductToCart(item)
        }


    }

    fun removeItem(item: Product) {
        _productList.value?.find { it.name == item.name }?.removeQty()
        _productList.value = _productList.value?.toList()

        viewModelScope.launch {
            repository.removeProductFromCart(item)
        }
    }

    private fun onFetchStoreSuccess(store: Store) {
        _storeInfo.value = store
    }

    private fun onFetchStoreFailed(failure: Failure) {
        //TODO

    }

    private fun onFetchProductSuccess(items: List<Product>) {
        _productList.value = items
    }

    private fun onFetchProductFailed(failure: Failure) {
        //TODO

    }

    private fun combineStoreProductData(
        store: Store?,
        productList: List<Product>?
    ): List<ParentListItemModel> {
        val mergeList: MutableList<ParentListItemModel> = mutableListOf<ParentListItemModel>()

        store?.let {
            mergeList.add(it)
        }
        productList?.let {
            mergeList.addAll(productList)
        }
        return mergeList

    }

}