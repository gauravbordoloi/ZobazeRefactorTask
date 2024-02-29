package com.zobaze.zobazerefractortask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.databinding.ItemEmployeeBinding
import javax.inject.Inject

class EmployeeAdapter @Inject constructor(
    private var employeeAdapterListener: EmployeeAdapterListener
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    private var employees: List<EmployeeViewData>? = null

    fun setEmployees(employees: List<EmployeeViewData>) {
        val callback = EmployeeDiffUtil(this.employees, employees)
        val diffResult = DiffUtil.calculateDiff(callback)
        this.employees = employees
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employees.orEmpty().size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        employees?.getOrNull(position)?.let { data ->
            holder.bind(data)
            holder.itemView.setOnClickListener {
                employeeAdapterListener.onEmployeeClicked(data)
            }
        }
    }

}