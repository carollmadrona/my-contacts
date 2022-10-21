package com.example.mycontacts.di.component

import com.example.mycontacts.base.BaseActivity
import com.example.mycontacts.base.BaseApplication
import com.example.mycontacts.di.module.AdapterModule
import com.example.mycontacts.di.module.NetworkModule
import com.example.mycontacts.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, AdapterModule::class]
)
interface AppComponent {
    fun inject(app: BaseApplication)
    fun inject(activity: MainActivity)

}