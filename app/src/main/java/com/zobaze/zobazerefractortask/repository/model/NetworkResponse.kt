package com.zobaze.zobazerefractortask.repository.model

import androidx.annotation.Keep

@Keep
data class NetworkResponse<T>(
    val status: String?,
    val data: T? = null
) {

    val isSuccess = (status == "success")

}