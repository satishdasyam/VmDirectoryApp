package com.dasyam.vmdirectoryapp.employees.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dasyam.vmdirectoryapp.common.networking.GlideApp
import com.dasyam.vmdirectoryapp.databinding.ItemEmployeeBinding
import com.dasyam.vmdirectoryapp.employees.Employee
import java.util.*

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>(),
    Filterable {

    private val employeeList = mutableListOf<Employee>()
    private val searchList = mutableListOf<Employee>()
    private val circleCrop = CircleCrop()
    private var isSearchClicked = false

    val employeeClickedObserver by lazy {
        MutableLiveData<Employee>()
    }

    class EmployeeViewHolder(val itemBinding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemBinding = ItemEmployeeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = if (isSearchClicked) searchList[position] else employeeList[position]
        holder.itemBinding.tvEmpName.text = employee.getFullName()
        holder.itemBinding.tvJob.text = employee.jobTitle
        holder.itemBinding.root.setOnClickListener {
            employeeClickedObserver.value = employee
        }
        GlideApp.with(holder.itemView.context)
            .load(employee.imagePath)
            .transform(circleCrop)
            .into(holder.itemBinding.ivEmp)
        holder.itemBinding.root.contentDescription = "Employee ${position}: " +
                "${employee.getFullName()}, Job title ${employee.jobTitle}"
    }

    override fun getItemCount(): Int {
        return if (isSearchClicked) searchList.size else employeeList.size
    }

    fun bindEmployees(empList: List<Employee>) {
        employeeList.clear()
        employeeList.addAll(empList)
        notifyDataSetChanged()
    }

    fun setIsSearchClicked(isSearch: Boolean) {
        isSearchClicked = isSearch
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val filterList = mutableListOf<Employee>()
                if (charSearch.isEmpty()) {
                    searchList.addAll(employeeList)
                } else {
                    for (row in employeeList) {
                        if (row.getFullName().lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            filterList.add(row)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                searchList.clear()
                searchList.addAll(results?.values as List<Employee>)
                notifyDataSetChanged()
            }

        }
    }

    fun getEmployeeList(): List<Employee> {
        return employeeList
    }
}