package com.example.cleanarchitectureandroidstudying.di

import android.app.Application
import com.example.data.di.dataModule
import com.example.data.di.databaseModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@KoinApplication)
            modules(listOf(mainModule, databaseModule, domainModule, dataModule ))

        }
    }
}