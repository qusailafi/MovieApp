package com.example.movie.application
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp//To make application use hilt.
class App: Application() {
    companion object{
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext=this
    }
}