package com.example.mycontacts.base

import android.app.Application
import com.example.mycontacts.di.component.DaggerAppComponent


class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
    }
}