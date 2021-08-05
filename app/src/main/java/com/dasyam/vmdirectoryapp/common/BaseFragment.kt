package com.dasyam.vmdirectoryapp.common

import androidx.fragment.app.Fragment
import com.dasyam.vmdirectoryapp.common.dependency_injection.ScreenCompositionRoot

abstract class BaseFragment : Fragment() {

    fun getCompositionRoot(): ScreenCompositionRoot {
        return (requireActivity() as BaseActivity).getCompositionRoot()
    }

}