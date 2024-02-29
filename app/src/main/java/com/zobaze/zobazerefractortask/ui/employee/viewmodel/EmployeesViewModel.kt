package com.zobaze.zobazerefractortask.ui.employee.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.ZobazeApp
import com.zobaze.zobazerefractortask.adapter.EmployeeViewData
import com.zobaze.zobazerefractortask.repository.EmployeeRepository
import com.zobaze.zobazerefractortask.repository.model.EmployeeModel
import com.zobaze.zobazerefractortask.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val app: ZobazeApp,
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    sealed class Event {
        data class Employees(val employees: List<EmployeeViewData>) : Event()

        data class Error(val message: String?) : Event()
    }

    private val flowChannel = Channel<Event>(Channel.BUFFERED)
    val flowCollector = flowChannel.receiveAsFlow()

    fun fetchEmployees(ids: Array<Int>) {
        viewModelScope.launchIO({
            Log.e("fetchEmployees", it.message, it)
            flowChannel.send(Event.Error(it.message))
        }) {
            val response = employeeRepository.getEmployees()
            if (response.status != "success") {
                throw Exception(response.message ?: app.getString(R.string.api_failed))
            }
            if (response.data.isNullOrEmpty()) {
                throw Exception(app.getString(R.string.no_data_found))
            }

            val rawEmployees = Array(ids.size) { _ -> mutableListOf<EmployeeModel>() }
            response.data.forEach { em ->
                ids.forEachIndexed { i, id ->
                    if (em.id % id == 0) {
                        rawEmployees[i].add(em)
                    }
                }
            }

            flowChannel.send(
                Event.Employees(
                    EmployeeViewData.convert(rawEmployees.flatMap { it })
                )
            )
        }
    }

}