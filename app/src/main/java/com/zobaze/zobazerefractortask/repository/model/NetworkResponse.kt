package com.zobaze.zobazerefractortask.repository.model

import androidx.annotation.Keep

@Keep
data class NetworkResponse<T>(
    val status: String?,
    val message: String?,
    val data: T? = null
) {

    companion object {

        fun <T> error(status: String?, message: String?) = NetworkResponse<T>(status, message)

    }

}