package com.saiful.opn_estore.api

import com.saiful.opn_estore.data.model.OrderRequestBody
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SwaggerAPIService {

    @GET("storeInfos")
    suspend fun fetchStores(): Response<Store>

    @GET("productss")
    suspend fun fetchProducts(): Response<List<Product>>

    @POST("order")
    suspend fun submitOrder(@Body body:OrderRequestBody):Response<Unit>
}