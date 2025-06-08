package com.kronos

import android.app.Application
import io.scanbot.sdk.ScanbotSDKInitializer

class MyApplication : Application() {

    companion object {
        private const val LICENSE_KEY =
            "aTDb5BVb6rhFO3f85renINXffyInr9" +
                    "RCldAwhZ1pDM5bdnBKYAok9qf7pW4m" +
                    "2uv7eHJZ89EyIeRHoUY2+P9Mc2RGfM" +
                    "K0Is8k9LXWhcWwObOQDC/KhXFIWWvj" +
                    "PmpeosL7Ha5+CMEa4eOs+d16fLNmtI" +
                    "GQTLs+VxEnNGYrJmRlR/iTL/gxuUpi" +
                    "srCLX6OxfGEe/opC6LC4Ox2J+tD45F" +
                    "XXiCzbMtVsTWIsOSbIpXGVDNjlw6A7" +
                    "14lg9hplRYpBOIgve8lMuAwDKSCzSL" +
                    "nOVkEJj57fSDb3Hw0Zs+9m9Nnxpyjy" +
                    "nPEqnhk+2HK99rllVKvGdpERzzgguz" +
                    "aX4SpJYl6xcg==\nU2NhbmJvdFNESw" +
                    "ptYXNzZW56aW9mZXJuYW5kb0BnbWFp" +
                    "bC5jb20KMTc0OTg1OTE5OQo4Mzg4Nj" +
                    "A3CjE5\n"
    }

    override fun onCreate() {
        super.onCreate()

        ScanbotSDKInitializer()
            .license(this, LICENSE_KEY)
            .prepareOCRLanguagesBlobs(true)
            .allowGpuAcceleration(false)
            .initialize(this)
    }
}
