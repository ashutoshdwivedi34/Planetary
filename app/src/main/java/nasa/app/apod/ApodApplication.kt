package nasa.app.apod

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApodApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}