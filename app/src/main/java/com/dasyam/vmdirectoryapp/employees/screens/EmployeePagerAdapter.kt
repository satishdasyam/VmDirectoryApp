package com.dasyam.vmdirectoryapp.employees.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dasyam.vmdirectoryapp.ofc_rooms.screens.OfcRoomListFragment

class EmployeePagerAdapter(fa: FragmentActivity) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                EmployeeListFragment.newInstance()
            }
            1 -> {
                OfcRoomListFragment.newInstance()
            }
            else -> {
                EmployeeListFragment.newInstance()
            }
        }
    }

}




