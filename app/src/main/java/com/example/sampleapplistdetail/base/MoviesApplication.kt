package com.example.sampleapplistdetail.base

import android.app.Application
import android.content.Context
import com.example.sampleapplistdetail.injection.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(base!!)
            androidFileProperties()
            modules(listOf(apiModule, repositoryModule, useCaseModule, netModule))
        }
        super.attachBaseContext(base)
    }
}