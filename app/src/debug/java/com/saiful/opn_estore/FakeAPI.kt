package com.saiful.opn_estore

import com.saiful.opn_estore.utils.Either
import com.saiful.opn_estore.utils.Failure
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store

class FakeAPI {
    var setSuccessResponse = true
    var setEmptyData = true
    fun getStores(): Either<Failure, Store> {
        return if(setSuccessResponse){
            return Either.Success(Store("Tea shop", 4, "10:00", "20:00"))
        }else{
            Either.Error(Failure.ServerError())
        }
    }

    fun getProducts(): Either<Failure, List<Product>> {

        return if(setSuccessResponse){
            val temp = mutableListOf<Product>()
            temp.add(Product("Product 1", 40, "", 0))
            temp.add(Product("Product 2", 50, "", 0 ))
            temp.add(Product("Product 3", 60, "", 0))
            temp.add(Product("Product 4", 30, "", 0))
            temp.add(Product("Product 5", 40, "", 0))
            temp.add(Product("Product 6", 80, "", 0))
            return Either.Success(temp)
        }else{
            Either.Error(Failure.ServerError())
        }

    }

    fun submitOrder(data: OrderRequestBody): Either<Failure, Unit> {
        return if(setSuccessResponse){
            Either.Success(Unit)
        }else{
            Either.Error(Failure.ServerError())
        }
    }

    fun updateProduct(product: Product) {

    }

    fun clearAllProductFromCart() {
    }

    fun getAllCart(): List<Product> {
        val temp = mutableListOf<Product>()
        temp.add(Product("Product 1", 40, "", 1))
        temp.add(Product("Product 2", 50, "", 1))
        temp.add(Product("Product 3", 60, "", 1))
        return temp

    }
}