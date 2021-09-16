package com.saiful.opn_estore.data.model

data class Product(
    val name: String,
    val price: Int,
    val imageUrl: String,
    var qty: Int = 0
) : ParentListItemModel(){
    fun addQty(){
        qty++
    }
    fun removeQty(){
        qty--
    }
}