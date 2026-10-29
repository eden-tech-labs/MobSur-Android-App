package com.example.mobsurandroiddemo

import android.app.Application
import io.edentechlabs.survey.sdk.MobSur

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // userId = null: the SDK generates a random ID once and keeps it across launches.
        // A new ID on every launch would reset survey state and count as a new tracked user.
        MobSur.setup(this, APP_ID, userId = null, debug = BuildConfig.DEBUG)
    }

    companion object {
        // App ID: https://app.mobsur.com/user/apps
        const val APP_ID = "36121162-25d6-40f1-ab24-d5bd87a47601"
    }
}
