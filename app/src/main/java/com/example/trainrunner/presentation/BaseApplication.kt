package com.example.trainrunner.presentation

import android.app.Application

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}