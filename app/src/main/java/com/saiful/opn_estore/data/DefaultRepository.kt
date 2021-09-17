package com.saiful.opn_estore.data

import com.saiful.opn_estore.api.SwaggerAPIService
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.repository.Repository
import com.saiful.opn_estore.utils.Either
import com.saiful.opn_estore.utils.Failure
import com.saiful.opn_estore.utils.handleApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val apiService:SwaggerAPIService, private val cartDao: CartDao):Repository{

    override suspend fun getStores():Either<Failure, Store>{
        return handleApiResponse(apiService.fetchStores())
    }

    override suspend fun getProducts():Either<Failure, List<Product>>{
        return handleApiResponse(apiService.fetchProducts())
    }

    override suspend fun submitOrder(data: OrderRequestBody):Either<Failure, Unit>{
        return handleApiResponse(apiService.submitOrder(data))
    }

    override suspend fun updateProduct(product: Product){
        cartDao.addProduct(product)
    }

    override suspend fun clearAllProductFromCart(){
        return cartDao.deleteAll()
    }

    override suspend fun getAllCart():List<Product>{
        return cartDao.getAllProductList()
    }
}