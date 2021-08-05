package com.dasyam.vmdirectoryapp.common.dependency_injection

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.dasyam.vmdirectoryapp.common.ScreensNavigator
import com.dasyam.vmdirectoryapp.common.utils.KeyBoardHelper
import com.dasyam.vmdirectoryapp.employees.FetchEmployeeDetailsUseCase
import com.dasyam.vmdirectoryapp.ofc_rooms.FetchOfcRoomDetailsUseCase

class ScreenCompositionRoot(
    private val compositionRoot: AppCompositionRoot,
    private val activity: AppCompatActivity
) {

    fun getFetchEmployeeDetailsUseCase(): FetchEmployeeDetailsUseCase {
        return FetchEmployeeDetailsUseCase(compositionRoot.getVmApiHelper())
    }

    fun getFetchOfcRoomDetailsUseCase(): FetchOfcRoomDetailsUseCase {
        return FetchOfcRoomDetailsUseCase(compositionRoot.getVmApiHelper())
    }

    fun getScreensNavigator(): ScreensNavigator {
        return ScreensNavigator(getContext())
    }

    private fun getContext(): Context {
        return activity
    }

    fun getKeyBoardHelper(): KeyBoardHelper {
        return KeyBoardHelper(activity)
    }
}