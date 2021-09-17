package com.saiful.opn_estore.utils

import com.saiful.opn_estore.data.Either
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.repository.Repository

class FakeRepository :Repository {
    override suspend fun getStores(): Either<Failure, Store> {
        return Either.Success(Store("Coffee Store", 4.5, "10:00", "20:00"))
    }

    override suspend fun getProducts(): Either<Failure, List<Product>> {
        TODO("Not yet implemented")

    }

    override suspend fun submitOrder(data: OrderRequestBody): Either<Failure, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllProductFromCart() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCart(): List<Product> {
        TODO("Not yet implemented")
    }
}