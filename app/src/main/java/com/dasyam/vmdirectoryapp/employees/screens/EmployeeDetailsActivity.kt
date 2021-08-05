package com.dasyam.vmdirectoryapp.employees.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dasyam.vmdirectoryapp.common.BaseActivity
import com.dasyam.vmdirectoryapp.common.networking.GlideApp
import com.dasyam.vmdirectoryapp.databinding.ActivityEmployeeDetailsBinding
import com.dasyam.vmdirectoryapp.employees.Employee

class EmployeeDetailsActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityEmployeeDetailsBinding

    companion object {
        private const val INTENT_EXTRA_EMPLOYEE = "intent_extra_employee"
        fun start(context: Context, employeeDetails: Employee) {
            val intent = Intent(context, EmployeeDetailsActivity::class.java).apply {
                putExtra(INTENT_EXTRA_EMPLOYEE, employeeDetails)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        initToolBarWithUpButton()
        initData()
    }

    private fun initLayout() {
        viewBinding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
    }

    private fun initToolBarWithUpButton() {
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    @SuppressLint("Range")
    private fun initData() {
        val employee: Employee? = intent.getParcelableExtra(INTENT_EXTRA_EMPLOYEE)
        if (employee != null) {
            viewBinding.tvEmail.setText(employee.email)
            viewBinding.tvEmpName.setText(employee.getFullName())
            viewBinding.tvPhone.setText(employee.phoneNo)
            viewBinding.tvJob.setText(employee.jobTitle)
            GlideApp.with(this)
                .load(employee.imagePath)
                .transform(CircleCrop())
                .into(viewBinding.ivEmp)
            viewBinding.toolbar.setBackgroundColor(Color.parseColor(employee.favouriteColor))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}