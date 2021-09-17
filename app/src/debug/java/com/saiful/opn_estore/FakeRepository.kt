package com.saiful.opn_estore

import com.saiful.opn_estore.data.Either
import com.saiful.opn_estore.data.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.repository.Repository

class FakeRepository :Repository {

    val fakeAPI = FakeAPI()

    override suspend fun getStores(): Either<Failure, Store> {
        return fakeAPI.getStores()
    }

    override suspend fun getProducts(): Either<Failure, List<Product>> {
        return fakeAPI.getProducts()

    }

    override suspend fun submitOrder(data: OrderRequestBody): Either<Failure, Unit> {
        return fakeAPI.submitOrder(data)
    }


    override suspend fun updateProduct(product: Product) {
        //Fake update product for DB can be empty for easy testing
    }

    override suspend fun clearAllProductFromCart() {

        //Fake deleteAll product for DB can be empty for easy testing
    }

    override suspend fun getAllCart(): List<Product> {
        return fakeAPI.getAllCart()
    }
}