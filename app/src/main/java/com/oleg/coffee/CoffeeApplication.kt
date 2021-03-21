package com.oleg.coffee

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Crafted by Lukman on 21/03/2021.
 **/
@HiltAndroidApp
class CoffeeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}