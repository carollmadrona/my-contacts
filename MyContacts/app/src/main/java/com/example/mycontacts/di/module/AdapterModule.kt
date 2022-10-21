package com.example.mycontacts.di.module

import com.example.mycontacts.view.UserAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AdapterModule {

    @Singleton
    @Provides
    fun providesUserAdapter() : UserAdapter = UserAdapter()
}