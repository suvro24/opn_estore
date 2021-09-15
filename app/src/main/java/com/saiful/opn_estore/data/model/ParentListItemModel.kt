package com.saiful.opn_estore.data.model

sealed class ParentListItemModel


sealed class Result {
    data class Success(val data : List<String>) : Result()
    data class Failure(val exception : String) : Result()
}