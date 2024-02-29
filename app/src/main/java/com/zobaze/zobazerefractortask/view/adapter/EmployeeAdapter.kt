package com.zobaze.zobazerefractortask.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.databinding.ItemEmployeeBinding
import javax.inject.Inject

class EmployeeAdapter @Inject constructor() : RecyclerView.Adapter<EmployeeViewHolder>() {

    private var employees: List<EmployeeViewData>? = null

    fun setEmployees(employees: List<EmployeeViewData>) {
        //Diff util is not required
        this.employees = employees
        notifyDataSetChanged()
        //TODO: Add diff util
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
        employees?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

}