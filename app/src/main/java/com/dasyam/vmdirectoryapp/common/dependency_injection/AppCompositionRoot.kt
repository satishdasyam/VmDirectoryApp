package com.dasyam.vmdirectoryapp.common.dependency_injection

import android.content.Context
import com.dasyam.vmdirectoryapp.BuildConfig
import com.dasyam.vmdirectoryapp.common.networking.VmApiHelper
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot(private val applicationContext: Context) {

    private val apiHelper: VmApiHelper by lazy {
        getRetrofit().create(VmApiHelper::class.java)
    }

    fun getVmApiHelper(): VmApiHelper {
        return apiHelper
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                )
            )
            .build()
    }
}