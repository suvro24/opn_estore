package com.saiful.opn_estore.utils


sealed class Either<out L, out R> {
    data class Error<out L>(val failure: L) : Either<L, Nothing>()
    data class Success<out R>(val data: R) : Either<Nothing, R>()

    fun successOrError(functionSuccess: (R) -> Any, functionFailure: (L) -> Any): Any =
        when (this) {
            is Success -> functionSuccess(data)
            is Error -> functionFailure(failure)
        }
}

sealed class Failure {
    class NetworkConnection(msg:String="") : Failure()
    class ServerError(code: Int = 1, error: String? = null, errorBody: String? = null) : Failure()
    object ParsingError : Failure()
}