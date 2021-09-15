package com.saiful.opn_estore.api

import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import retrofit2.Response
import retrofit2.http.GET

interface SwaggerAPIService {

    @GET("storeInfo")
    suspend fun fetchStores(): Response<Store>

    @GET("products")
    suspend fun fetchProducts(): Response<List<Product>>
}