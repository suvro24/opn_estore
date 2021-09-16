package com.saiful.opn_estore.data


import androidx.lifecycle.LiveData
import com.saiful.opn_estore.api.SwaggerAPIService
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.utils.handleApiResponse
import kotlinx.coroutines.flow.Flow
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
    fun getAllProductFromCart():Flow<List<Product>>{
        return cartDao.getAllProduct()
    }
}