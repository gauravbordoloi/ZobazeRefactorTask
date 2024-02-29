package com.zobaze.zobazerefractortask.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zobaze.zobazerefractortask.R
import com.zobaze.zobazerefractortask.adapter.EmployeeAdapter
import com.zobaze.zobazerefractortask.adapter.EmployeeAdapterListener
import com.zobaze.zobazerefractortask.adapter.EmployeeViewData
import com.zobaze.zobazerefractortask.databinding.FragmentEmployeesBinding
import com.zobaze.zobazerefractortask.ui.employee.viewmodel.EmployeesViewModel
import com.zobaze.zobazerefractortask.util.observeInLifecycle
import com.zobaze.zobazerefractortask.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeesFragment : Fragment(), EmployeeAdapterListener {

    private var _binding: FragmentEmployeesBinding? = null
    private lateinit var employeesViewModel: EmployeesViewModel

    @Inject
    lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentEmployeesBinding.inflate(layoutInflater)
        }
        return requireBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
        fetchData()
    }

    private fun initViews() {
        requireBinding().recyclerView.apply {
            setHasFixedSize(true)
            adapter = employeeAdapter
        }
    }

    /**
     * Observe for employee data here
     */
    private fun observeData() {
        employeesViewModel.flowCollector.observeInLifecycle(viewLifecycleOwner) {
            when (it) {
                is EmployeesViewModel.Event.Employees -> {
                    employeeAdapter.setEmployees(it.employees)
                }

                is EmployeesViewModel.Event.Error -> {
                    requireContext().showShortToast(it.message)
                }
            }
        }
    }

    private fun fetchData() {
        requireContext().showShortToast(getString(R.string.retrieving_employees))
        //You can pass the logic in argument as required
        employeesViewModel.fetchEmployees(arrayOf(3, 7, 4))
    }

    private fun initViewModel() {
        employeesViewModel = ViewModelProvider(this)[EmployeesViewModel::class.java]
    }

    private fun requireBinding(): FragmentEmployeesBinding {
        if (_binding == null) {
            throw IllegalStateException("Binding is not initialized")
        }
        return _binding!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEmployeeClicked(employeeViewData: EmployeeViewData) {
        requireContext().showShortToast(employeeViewData.name)
    }

}