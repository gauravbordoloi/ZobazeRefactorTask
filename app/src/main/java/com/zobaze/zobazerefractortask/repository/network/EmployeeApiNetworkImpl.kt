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
        return networkEmployeeApiV1.getEmployees()
    }

}