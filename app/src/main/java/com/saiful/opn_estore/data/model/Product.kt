package com.saiful.opn_estore.data.model

data class Product(
    val name:String,
    val price: Number,
    val imageUrl:String,
) : ParentListItemModel()