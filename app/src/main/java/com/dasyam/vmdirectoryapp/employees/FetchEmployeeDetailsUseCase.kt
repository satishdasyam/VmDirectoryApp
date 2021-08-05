package com.dasyam.vmdirectoryapp.employees

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dasyam.vmdirectoryapp.common.networking.VmApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchEmployeeDetailsUseCase(private val vmApiHelper: VmApiHelper) {

    val employeeList: MutableLiveData<List<Employee>> by lazy {
        MutableLiveData<List<Employee>>()
    }

    val employeeUseCaseFailure: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun fetchEmployeeDetails() {
        vmApiHelper.getEmployeeList().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                employeeList.value = response.body()
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.d("RESULT", "failure:" + Log.getStackTraceString(t))
                employeeUseCaseFailure.value = true
            }

        })
    }

}