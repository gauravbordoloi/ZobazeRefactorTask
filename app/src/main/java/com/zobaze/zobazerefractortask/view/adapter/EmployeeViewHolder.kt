package com.zobaze.zobazerefractortask.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.databinding.ItemEmployeeBinding

class EmployeeViewHolder(
    private val binding: ItemEmployeeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: EmployeeViewData) {
        binding.employee = data
    }

}