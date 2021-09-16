package com.saiful.opn_estore.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.net.Inet4Address

data class OrderRequestBody constructor(
    @Expose
    @SerializedName("products")
    var products: List<Product> = emptyList(),
    @Expose
    @SerializedName("delivery_address")
    var address: String = ""
)