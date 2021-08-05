package com.dasyam.vmdirectoryapp.ofc_rooms

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dasyam.vmdirectoryapp.common.networking.VmApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchOfcRoomDetailsUseCase(private val vmApiHelper: VmApiHelper) {

    val ofcRoomList: MutableLiveData<List<OfcRoom>> by lazy {
        MutableLiveData<List<OfcRoom>>()
    }

    val ofcRoomUseCaseFailure: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    fun fetchOfcRoomDetails() {
        vmApiHelper.getOfcRoomList().enqueue(object : Callback<List<OfcRoom>> {
            override fun onResponse(
                call: Call<List<OfcRoom>>,
                response: Response<List<OfcRoom>>
            ) {
                ofcRoomList.value = response.body()
            }

            override fun onFailure(call: Call<List<OfcRoom>>, t: Throwable) {
                Log.d("RESULT", "failure:" + Log.getStackTraceString(t))
                ofcRoomUseCaseFailure.value = true
            }

        })
    }
}