package com.saiful.opn_estore.data.model

import com.google.gson.annotations.Expose

data class Store(
    @Expose
    val name: String,
    @Expose
    val rating: Number,
    @Expose
    val openingTime: String,
    @Expose
    val closingTime: String
)