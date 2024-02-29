package com.zobaze.zobazerefractortask.repository.network

import com.zobaze.zobazerefractortask.repository.model.EmployeeModel
import com.zobaze.zobazerefractortask.repository.model.NetworkResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeApiNetworkImpl @Inject constructor(
    private val networkEmployeeApiV1: NetworkEmployeeApiV1
) : EmployeeApi {

    override suspend fun getEmployees(): NetworkResponse<List<EmployeeModel>> {
        val res = networkEmployeeApiV1.getEmployees()
        return when {
            res.code() == 200 && res.body() != null -> res.body()!!
            res.code() == 429 -> NetworkResponse.error("error", "Too many requests")
            else -> NetworkResponse.error("error", res.errorBody()?.string() ?: "Unknown error")
        }
    }

}