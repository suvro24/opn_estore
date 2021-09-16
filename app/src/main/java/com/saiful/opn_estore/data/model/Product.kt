package com.saiful.opn_estore.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "carts")
data class Product constructor(
    @PrimaryKey
    @Expose
    @SerializedName("name")
    var name: String = "",
    @Expose
    @SerializedName("price")
    var price: Int = 0,
    @Expose
    @SerializedName("imageUrl")
    var imageUrl: String = "",

    var qty: Int = 0
) {
    fun addQty(){
        qty++
    }
    fun removeQty(){
        qty--
    }
    fun updateQty(q:Int){
        qty = q
    }
}