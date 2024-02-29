package com.zobaze.zobazerefractortask.di

import androidx.fragment.app.Fragment
import com.zobaze.zobazerefractortask.adapter.EmployeeAdapterListener
import com.zobaze.zobazerefractortask.ui.employee.EmployeesFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object AdapterModule {

    @Provides
    fun provideEmployeeAdapterListener(fragment: Fragment): EmployeeAdapterListener {
        return when (fragment) {
            is EmployeesFragment -> fragment
            else -> throw IllegalStateException("Please cast the fragment in provideEmployeeAdapterListener")
        }
    }


}