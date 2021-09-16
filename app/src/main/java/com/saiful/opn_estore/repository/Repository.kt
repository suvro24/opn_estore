package com.saiful.opn_estore.repository

import com.saiful.opn_estore.data.Either
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store

interface Repository {
    suspend fun getStores(): Either<Failure, Store>

    suspend fun getProducts(): Either<Failure, List<Product>>

    suspend fun submitOrder(data: OrderRequestBody): Either<Failure, Unit>

    suspend fun updateProduct(product: Product)

    suspend fun clearAllProductFromCart()

    suspend fun getAllCart(): List<Product>
}