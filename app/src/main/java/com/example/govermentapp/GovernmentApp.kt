package com.example.govermentapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GovernmentApp: Application() {
    companion object {
        lateinit var instance : GovernmentApp
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}