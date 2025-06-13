package com.kronos

import android.app.Application
import io.scanbot.sdk.ScanbotSDKInitializer

class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        ScanbotSDKInitializer()
            .prepareOCRLanguagesBlobs(true)
            .initialize(this)

    }
}
