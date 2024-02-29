package com.zobaze.zobazerefractortask.repository.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

/**
 * @Keep the class from obfuscation as data is coming from BE
 *
 */

@Keep
data class EmployeeModel(
    val id: Int,
    @Json(name = "employee_name")
    val name: String?
)