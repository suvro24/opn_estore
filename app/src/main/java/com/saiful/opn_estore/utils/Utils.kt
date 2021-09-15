package com.saiful.opn_estore.utils

import android.util.Log
import com.saiful.opn_estore.data.Either
import com.saiful.opn_estore.data.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

@Suppress("BlockingMethodInNonBlockingContext")
suspend inline fun <T> handleApiResponse(response: Response<T>): Either<Failure, T> {
    return withContext(Dispatchers.IO){
        try {
            if (response.isSuccessful && response.body() != null) {

                Either.Success(response.body()!!)
            } else {

                val code = response.code()
                val errorBody = response.errorBody()?.string()
                if (code == 400) {
                    val body = response.errorBody()
                    Either.Error(Failure.ServerError(400, "Bad request", errorBody))
                }
                else if (code == 401) {
                    Either.Error(Failure.ServerError(401, "Unauthorized request", errorBody))
                } else if (code == 404) {
                    Either.Error(Failure.ServerError(404, "Resource not found", errorBody))
                } else if (code == 500) {
                    Either.Error(Failure.ServerError(404, "Internal server error", errorBody))
                } else {
                    Either.Error(Failure.ServerError(-1, "Unknown server error", null))
                }

            }
        } catch (e: Exception) {
            Either.Error(Failure.ParsingError)
        }
    }



}