package com.saiful.opn_estore.data


import com.saiful.opn_estore.api.SwaggerAPIService
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.utils.handleApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val apiService:SwaggerAPIService, private val cartDao: CartDao){

    suspend fun getStores():Either<Failure, Store>{
        return handleApiResponse(apiService.fetchStores())
    }

    suspend fun getProducts():Either<Failure, List<Product>>{
        return handleApiResponse(apiService.fetchProducts())
    }

    suspend fun addProductToCart(product: Product){
        cartDao.addProduct(product)
    }
    suspend fun removeProductFromCart(product: Product){
        cartDao.addProduct(product)
    }
    suspend fun getAllProductFromCart():List<Product>{
        return cartDao.getAllProduct()
    }
}