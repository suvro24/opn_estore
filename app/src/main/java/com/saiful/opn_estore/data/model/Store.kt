package com.saiful.opn_estore.data.model

import com.google.gson.annotations.SerializedName

data class Store(
    @field:SerializedName("name")val name:String,
    @field:SerializedName("rating")val rating: Number,
    @field:SerializedName("openingTime")val openingTime:String,
    @field:SerializedName("closingTime")val closingTime:String

):ParentListItemModel()