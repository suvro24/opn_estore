package com.saiful.opn_estore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saiful.opn_estore.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Query("SELECT * FROM carts WHERE qty>0")
    suspend fun getAllProductList(): List<Product>

    @Query("DELETE FROM carts")
    suspend fun deleteAll()
}