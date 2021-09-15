package com.saiful.opn_estore.data.model

import android.os.Parcelable

data class Store(
    val title:String,
    val rating: Number,
    val openingTime:String,
    val closingTime:String

):ParentListItemModel()