package com.ediel.mv.recipely

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipelyApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}