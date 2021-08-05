package com.dasyam.vmdirectoryapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.dasyam.vmdirectoryapp.common.BaseActivity
import com.dasyam.vmdirectoryapp.databinding.ActivityMainBinding
import com.dasyam.vmdirectoryapp.employees.screens.EmployeePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity() {

    lateinit var viewBinding: ActivityMainBinding
    val isSearchClicked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    private var tabIcons = intArrayOf(
        R.drawable.ic_baseline_people_alt_24,
        R.drawable.ic_baseline_meeting_room_24
    )

    private var tabContentDescriptions = intArrayOf(
        R.string.ab_employees_tab,
        R.string.ab_ofc_rooms
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        initTabs()
    }

    private fun initTabs() {
        val pagerAdapter = EmployeePagerAdapter(this)
        viewBinding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(viewBinding.tabs, viewBinding.viewPager) { tab, position ->
            tab.contentDescription = resources.getString(tabContentDescriptions[position])
            tab.icon = ResourcesCompat.getDrawable(
                resources,
                tabIcons[position], theme
            )
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        menu?.findItem(R.id.clear)?.isVisible = isSearchClicked.value!!
        menu?.findItem(R.id.search)?.isVisible = !isSearchClicked.value!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                isSearchClicked.value = true
                viewBinding.evSearchText.visibility = View.VISIBLE
                //viewBinding.searchView.visibility = View.VISIBLE
                invalidateOptionsMenu()
                viewBinding.evSearchText.requestFocus()
                getCompositionRoot().getKeyBoardHelper().showKeyBoard(viewBinding.evSearchText)
                true
            }
            R.id.clear -> {
                isSearchClicked.value = false
                viewBinding.evSearchText.editableText.clear()
                viewBinding.evSearchText.visibility = View.GONE
                invalidateOptionsMenu()
                getCompositionRoot().getKeyBoardHelper().hideKeyBoard(viewBinding.evSearchText)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }
}