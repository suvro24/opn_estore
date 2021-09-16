package com.saiful.opn_estore.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "carts")
data class Product(
    @PrimaryKey
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