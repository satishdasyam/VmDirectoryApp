package com.dasyam.vmdirectoryapp.common

import android.content.Context
import com.dasyam.vmdirectoryapp.employees.Employee
import com.dasyam.vmdirectoryapp.employees.screens.EmployeeDetailsActivity

class ScreensNavigator(private val context: Context) {

    fun toEmployeeDetailsScreen(employeeDetails: Employee) {
        EmployeeDetailsActivity.start(context, employeeDetails)
    }
}