package com.saiful.opn_estore.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

@Suppress("BlockingMethodInNonBlockingContext")
suspend inline fun <T> handleApiResponse(response: Response<T>): Either<Failure, T> {
    return withContext(Dispatchers.IO) {
        try {
            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {

                val code = response.code()
                val msg = response.message()
                if (code == 400) {
                    Either.Error(Failure.ServerError(400, "Bad request", msg))
                } else if (code == 401) {
                    Either.Error(Failure.ServerError(401, "Unauthorized request", msg))
                } else if (code == 404) {
                    Either.Error(Failure.ServerError(404, "Resource not found", msg))
                } else if (code == 500) {
                    Either.Error(Failure.ServerError(404, "Internal server error", msg))
                } else {
                    Either.Error(Failure.ServerError(-1, "Unknown server error", null))
                }

            }
        } catch (e: Exception) {
            Either.Error(Failure.ParsingError)
        }
    }


}