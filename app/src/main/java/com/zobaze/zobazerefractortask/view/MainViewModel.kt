package com.zobaze.zobazerefractortask.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zobaze.zobazerefractortask.repository.EmployeeRepository
import com.zobaze.zobazerefractortask.util.launchIO
import com.zobaze.zobazerefractortask.view.adapter.EmployeeViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    sealed class Event {
        data class Employees(val employees: List<EmployeeViewData>) : Event()

        data class Error(val message: String?) : Event()
    }

    private val flowChannel = Channel<Event>(Channel.BUFFERED)
    val flowCollector = flowChannel.receiveAsFlow()

    fun fetchEmployees() {
        viewModelScope.launchIO({
            Log.e("fetchEmployees", it.message, it)
            flowChannel.send(Event.Error(it.message))
        }) {
            val response = employeeRepository.getEmployees()
            Log.e("data", response.toString())
//            if (!response.isSuccess) {
//                throw Exception("API Failed")
//            }
            if (response.data.isNullOrEmpty()) {
                throw Exception("No data")
            }
            flowChannel.send(Event.Employees(EmployeeViewData.convert(response.data)))
        }
    }

}