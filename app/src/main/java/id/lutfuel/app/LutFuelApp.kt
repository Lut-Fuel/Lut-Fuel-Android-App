package id.lutfuel.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LutFuelApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}