package com.zobaze.zobazerefractortask.repository.network

import com.zobaze.zobazerefractortask.repository.model.EmployeeModel
import com.zobaze.zobazerefractortask.repository.model.NetworkResponse

interface EmployeeApi {

    suspend fun getEmployees(): NetworkResponse<List<EmployeeModel>>

}