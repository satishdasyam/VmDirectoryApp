package com.dasyam.vmdirectoryapp.employees.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import com.dasyam.vmdirectoryapp.MainActivity
import com.dasyam.vmdirectoryapp.R
import com.dasyam.vmdirectoryapp.common.BaseFragment
import com.dasyam.vmdirectoryapp.databinding.FragmentEmployeeListBinding
import com.dasyam.vmdirectoryapp.employees.FetchEmployeeDetailsUseCase
import com.dasyam.vmdirectoryapp.ofc_rooms.screens.OfcRoomListFragment

class EmployeeListFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentEmployeeListBinding
    private lateinit var employeeDetailsUseCase: FetchEmployeeDetailsUseCase
    private val empListAdapter = EmployeeListAdapter()

    companion object {
        const val EMPLOYEE_BUNDLE = "emp_bundle"
        fun newInstance(): EmployeeListFragment {
            return EmployeeListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEmployeeListAdapter()
        employeeDetailsUseCase = getCompositionRoot().getFetchEmployeeDetailsUseCase()
        initObservers()
        employeeDetailsUseCase.fetchEmployeeDetails()
        showProgressBar()
        initSearch()
    }

    private fun initObservers() {
        employeeDetailsUseCase.employeeList.observe(viewLifecycleOwner, {
            empListAdapter.bindEmployees(it)
            hideProgressBar()
        })
        employeeDetailsUseCase.employeeUseCaseFailure.observe(viewLifecycleOwner, {
            empListAdapter.bindEmployees(emptyList())
            hideProgressBar()
        })

        (requireActivity() as MainActivity).isSearchClicked.observe(viewLifecycleOwner, {
            empListAdapter.setIsSearchClicked(it)
        })
    }

    private fun initEmployeeListAdapter() {
        viewBinding.rvEmpList.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.recycler_span_count)
        )
        viewBinding.rvEmpList.adapter = empListAdapter
        empListAdapter.employeeClickedObserver.observe(viewLifecycleOwner, {
            getCompositionRoot().getScreensNavigator().toEmployeeDetailsScreen(it)
        })
    }

    private fun initSearch() {
        requireActivity().findViewById<EditText>(R.id.ev_search_text)
            .addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    empListAdapter.filter.filter(s)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
    }

    private fun showProgressBar() {
        viewBinding.pbBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        viewBinding.pbBar.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EMPLOYEE_BUNDLE, ArrayList(empListAdapter.getEmployeeList()))
    }
}