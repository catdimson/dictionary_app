package com.example.dictionaryapp

import android.app.Application
import com.example.dictionaryapp.di.module.appModule
import com.example.dictionaryapp.di.module.historyScreen
import com.example.dictionaryapp.di.module.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModule, mainScreen, historyScreen)
        }
    }
}