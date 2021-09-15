package com.saiful.opn_estore.ui.store

import androidx.lifecycle.*
import com.saiful.opn_estore.data.model.ParentListItemModel
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor() : ViewModel() {


    private val _parentListItems: MutableLiveData<List<ParentListItemModel>> = MutableLiveData<List<ParentListItemModel>>()
    val parentListItems: LiveData<List<ParentListItemModel>> = _parentListItems


    init {
        val temp = mutableListOf<ParentListItemModel>()
        temp.add(Store("Store 1", 4.5, "", ""))
        temp.add(Store("Store 2", 4, "", ""))

        temp.add(Product("Product 1", 40, "",))
        temp.add(Product("Product 2", 40, "",))
        temp.add(Product("Product 3", 40, "",))
        temp.add(Product("Product 4", 40, "",))

        temp.add(Product("Product 5", 40, "",))
        temp.add(Product("Product 6", 40, "",))


        temp.add(Product("Product 7", 40, "",))
        temp.add(Product("Product 8", 40, "",))

        temp.add(Product("Product 9", 40, "",))
        temp.add(Product("Product 10", 40, "",))

        _parentListItems.value = temp


        viewModelScope.launch {
            delay(5000)
            _parentListItems.value = emptyList()
        }

    }
}