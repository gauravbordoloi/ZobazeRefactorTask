package com.zobaze.zobazerefractortask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zobaze.zobazerefractortask.databinding.ActivityMainBinding
import com.zobaze.zobazerefractortask.util.observeInLifecycle
import com.zobaze.zobazerefractortask.util.showShortToast
import com.zobaze.zobazerefractortask.view.adapter.EmployeeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initRecyclerView()
        observeData()
        mainViewModel.fetchEmployees()
    }

    private fun observeData() {
        mainViewModel.flowCollector.onEach {
            when (it) {
                is MainViewModel.Event.Employees -> {
                    employeeAdapter.setEmployees(it.employees)
                }

                is MainViewModel.Event.Error -> {
                    showShortToast(it.message)
                }
            }
        }.observeInLifecycle(this)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = employeeAdapter
        }
    }

    private fun loadDataFromServer() {
//        Thread {
//            try {
//                val urllllll =
//                    URL("https://dummy.restapiexample.com/api/v1/employees")
//                val urllll =
//                    URL("https://dummy.restapiexample.com/api/v1/employeees")
//                val urlde =
//                    URL("https://dummy.restapiexample.com/api/v1/employeeds/id")
//                val urldad =
//                    URL("https://dummy.restapiexample.com/api/v1/employees")
//                val urlad =
//                    URL("https://dummy.restapiexample.com/api/v1/employees")
//                val connection =
//                    urllllll.openConnection() as HttpURLConnection
//                val inputStream = connection.inputStream
//                val result = convertStreamToString(inputStream)
//                val jr = JSONObject(result)
//                val user = jr.getJSONArray("data")
//                for (i in 0 until user.length()) {
//                    val usr = user.getJSONObject(i)
//                    val id = usr.getInt("id")
//                    if (id % 3 == 0) {
//                        runOnUiThread {
//                            val viewww: View =
//                                layoutInflater.inflate(R.layout.listttttt, null)
//                            val nameView =
//                                viewww.findViewById<TextView>(R.id.ennnnn)
//                            val idView =
//                                viewww.findViewById<TextView>(R.id.idddd)
//                            nameView.text = usr.getString("employee_name")
//                            idView.text = id.toString()
//                            containerLayout!!.addView(viewww)
//                        }
//                    }
//                }
//                for (i in 0 until user.length()) {
//                    val employee = user.getJSONObject(i)
//                    val id = employee.getInt("id")
//                    if (id % 7 == 0) {
//                        runOnUiThread {
//                            val employeeView: View =
//                                layoutInflater.inflate(R.layout.listttttt, null)
//                            val nameView =
//                                employeeView.findViewById<TextView>(R.id.ennnnn)
//                            val idView =
//                                employeeView.findViewById<TextView>(R.id.idddd)
//                            nameView.text = employee.getString("employee_name")
//                            idView.text = id.toString()
//                            containerLayout!!.addView(employeeView)
//                        }
//                    }
//                }
//                for (i in 0 until user.length()) {
//                    val employee = user.getJSONObject(i)
//                    val id = employee.getInt("id")
//                    if (id % 4 == 0) {
//                        runOnUiThread {
//                            val employeeView: View =
//                                layoutInflater.inflate(R.layout.listttttt, null)
//                            val nameView =
//                                employeeView.findViewById<TextView>(R.id.ennnnn)
//                            val idView =
//                                employeeView.findViewById<TextView>(R.id.idddd)
//                            nameView.text = employee.getString("employee_name")
//                            idView.text = id.toString()
//                            containerLayout!!.addView(employeeView)
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }.start()
    }

}

