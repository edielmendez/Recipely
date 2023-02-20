package com.ediel.mv.recipely.ui.common

import androidx.fragment.app.Fragment

abstract class RecipelyBaseFragment: Fragment() {
    private val loader by lazy {
        LoaderFragment()
    }
    fun showLoader(){
        if (!loader.isAdded){
            loader.show(parentFragmentManager, LoaderFragment.TAG)
        }
    }

    fun hideLoader(){
        loader?.let {
            it.dismiss()
        }
    }
}