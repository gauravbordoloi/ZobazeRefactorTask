package com.zobaze.zobazerefractortask.view.adapter

import com.zobaze.zobazerefractortask.repository.model.EmployeeModel

data class EmployeeViewData(
    val id: Int,
    val name: String?
) {

    companion object {

        private fun convert(data: EmployeeModel): EmployeeViewData {
            return EmployeeViewData(data.id, data.name)
        }

        fun convert(data: List<EmployeeModel>): List<EmployeeViewData> {
            return data.map { convert(it) }
        }

    }

}