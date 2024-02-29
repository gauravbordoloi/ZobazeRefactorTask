package com.zobaze.zobazerefractortask.adapter

import androidx.recyclerview.widget.DiffUtil

class EmployeeDiffUtil(
    private val oldList: List<EmployeeViewData>? = null,
    private val newList: List<EmployeeViewData>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList?.size ?: 0

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList?.get(oldItemPosition)?.name == newList[newItemPosition].name
    }

}