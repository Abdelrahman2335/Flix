package com.example.flix

import android.app.Application
import android.util.Log

class FlixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Set up global exception handler to catch uncaught exceptions
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("FlixApplication", "Uncaught exception in thread: ${thread.name}", throwable)
            Log.e("FlixApplication", "Exception type: ${throwable.javaClass.simpleName}")
            Log.e("FlixApplication", "Exception message: ${throwable.message}")
            throwable.printStackTrace()

            // Call the default handler
            Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(thread, throwable)
        }

        Log.d("FlixApplication", "Application started")
    }
}

