package com.zobaze.zobazerefractortask.repository.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * @Keep the class from obfuscation as data is coming from BE
 *
 */

@Keep
data class EmployeeModel(
    val id: Int,
    @SerializedName("employee_name")
    val name: String?
)