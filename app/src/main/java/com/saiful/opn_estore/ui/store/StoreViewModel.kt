package com.saiful.opn_estore.ui.store

import android.util.Log
import android.widget.Toast
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
    private val storeInfo: LiveData<Store> = _storeInfo

    private var _productList: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    private var productList: LiveData<List<Product>> = _productList


    private val _parentListItems: MediatorLiveData<List<ParentListItemModel>> =
        MediatorLiveData<List<ParentListItemModel>>()

    //private val _parentListItems: MutableLiveData<List<ParentListItemModel>> = MutableLiveData<List<ParentListItemModel>>()
    val parentListItems: LiveData<List<ParentListItemModel>> = _parentListItems


    init {
        _parentListItems.addSource(storeInfo) {
            _parentListItems.value = combineStoreProductData(storeInfo.value, productList.value)
        }

        _parentListItems.addSource(productList) {
            _parentListItems.value = combineStoreProductData(storeInfo.value, productList.value)
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

        val selectedItem = _parentListItems.value?.find { it is Product && it.name == item.name }

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