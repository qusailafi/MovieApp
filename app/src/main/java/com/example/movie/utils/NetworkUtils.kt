package com.example.movie.utils
import android.content.Context
import android.net.ConnectivityManager
import com.example.movie.application.App
//this file used to checked on network status
object NetworkUtils {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            App.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }
}