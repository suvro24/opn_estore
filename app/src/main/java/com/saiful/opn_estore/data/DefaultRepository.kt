package com.saiful.opn_estore.data


import com.saiful.opn_estore.api.SwaggerAPIService
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.utils.handleApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(private val apiService:SwaggerAPIService){

    suspend fun getStores():Either<Failure, Store>{
        return handleApiResponse(apiService.fetchStores())
    }

    suspend fun getProducts():Either<Failure, List<Product>>{
        return handleApiResponse(apiService.fetchProducts())
    }
}