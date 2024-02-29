package com.zobaze.zobazerefractortask.repository.network

import com.zobaze.zobazerefractortask.repository.model.EmployeeModel
import com.zobaze.zobazerefractortask.repository.model.NetworkResponse
import retrofit2.http.GET

interface NetworkEmployeeApiV1 {

    @GET("v1/employees")
    suspend fun getEmployees(): NetworkResponse<List<EmployeeModel>>

}