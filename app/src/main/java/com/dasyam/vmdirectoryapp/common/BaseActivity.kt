package com.dasyam.vmdirectoryapp.common

import androidx.appcompat.app.AppCompatActivity
import com.dasyam.vmdirectoryapp.VmApp
import com.dasyam.vmdirectoryapp.common.dependency_injection.ScreenCompositionRoot

abstract class BaseActivity : AppCompatActivity() {

    private val mScreenCompositionRoot: ScreenCompositionRoot by lazy {
        ScreenCompositionRoot((application as VmApp).getCompositionRoot(), this)
    }

    fun getCompositionRoot(): ScreenCompositionRoot {
        return mScreenCompositionRoot
    }
}