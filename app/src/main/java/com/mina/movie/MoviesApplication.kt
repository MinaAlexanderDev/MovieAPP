package com.mina.movie

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            internal set
    }
}