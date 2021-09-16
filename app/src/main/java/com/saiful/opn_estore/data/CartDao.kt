package com.saiful.opn_estore.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saiful.opn_estore.data.model.Product

@Dao
interface CartDao {

    @Query("SELECT * FROM carts")
    suspend fun getAllProduct(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)
}