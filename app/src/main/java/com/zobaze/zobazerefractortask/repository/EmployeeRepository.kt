package com.zobaze.zobazerefractortask.repository

import com.zobaze.zobazerefractortask.repository.model.EmployeeModel
import com.zobaze.zobazerefractortask.repository.model.NetworkResponse
import com.zobaze.zobazerefractortask.repository.network.EmployeeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val employeeApi: EmployeeApi
) {

    suspend fun getEmployees(): NetworkResponse<List<EmployeeModel>> {
        return employeeApi.getEmployees()
    }

}