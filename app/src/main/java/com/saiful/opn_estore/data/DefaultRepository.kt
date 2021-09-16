package com.saiful.opn_estore.data

import androidx.lifecycle.LiveData
import com.saiful.opn_estore.api.SwaggerAPIService
import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.utils.handleApiResponse
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
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

    suspend fun submitOrder(data: OrderRequestBody):Either<Failure, Unit>{
        return handleApiResponse(apiService.submitOrder(data))
    }

    suspend fun updateProduct(product: Product){
        cartDao.addProduct(product)
    }

    suspend fun clearAllProductFromCart(){
        return cartDao.deleteAll()
    }

    suspend fun getAllCart():List<Product>{
        return cartDao.getAllProductList()
    }


}