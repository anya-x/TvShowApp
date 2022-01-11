package com.example.watchtvseries

import android.app.Application
import com.example.watchtvseries.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            koin.loadModules(listOf(appModule))
            koin.createRootScope()
        }
    }
}
