package com.dasyam.vmdirectoryapp.common.networking

import com.dasyam.vmdirectoryapp.employees.Employee
import com.dasyam.vmdirectoryapp.ofc_rooms.OfcRoom
import retrofit2.Call
import retrofit2.http.GET


interface VmApiHelper {
    @GET("people")
    fun getEmployeeList(): Call<List<Employee>>

    @GET("rooms")
    fun getOfcRoomList(): Call<List<OfcRoom>>
}