package com.saiful.opn_estore.data.model

import android.os.Parcelable

data class Product(
    val name:String,
    val price: Number,
    val imageUrl:String,
) : ParentListItemModel()