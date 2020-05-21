package com.calyrsoft.cleanmovieapp

import android.app.Application

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}