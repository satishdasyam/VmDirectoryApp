package com.dasyam.vmdirectoryapp

import android.app.Application
import com.dasyam.vmdirectoryapp.common.dependency_injection.AppCompositionRoot

class VmApp : Application() {

    private val mCompositionRoot: AppCompositionRoot by lazy {
        AppCompositionRoot(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun getCompositionRoot(): AppCompositionRoot {
        return mCompositionRoot
    }
}