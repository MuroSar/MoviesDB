package com.sartimau.moviesdb

import android.app.Application
import com.facebook.stetho.Stetho
import com.sartimau.di.repositoriesModule
import com.sartimau.di.useCasesModule
import com.sartimau.moviesdb.di.viewModelsModule
import io.realm.Realm
import org.koin.core.context.startKoin

class MoviesDBApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        Stetho.initializeWithDefaults(this)

        startKoin {
            modules(listOf(repositoriesModule, viewModelsModule, useCasesModule))
        }
    }
}