package com.zobaze.zobazerefractortask.repository.model

import androidx.annotation.Keep

/**
 * Created a generic class as backend might send data in the similar format for all APIs
 * Then we don't have to do code duplication
 */

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