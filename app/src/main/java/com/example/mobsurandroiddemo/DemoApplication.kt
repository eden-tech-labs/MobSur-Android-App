package com.example.mobsurandroiddemo

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import io.edentechlabs.survey.sdk.MobSur

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // userId = null: the SDK generates a random ID once and keeps it across launches.
        // A new ID on every launch would reset survey state and count as a new tracked user.
        // Debug builds use the test user from "New test user" instead, when there is one.
        MobSur.setup(
            this,
            BuildConfig.MOBSUR_APP_ID,
            userId = TestUser.get(this),
            debug = BuildConfig.DEBUG,
            baseUrl = BuildConfig.MOBSUR_BASE_URL,
        )
    }
}

/** Debug-only test user, so QA can answer the same surveys again without reinstalling. */
object TestUser {
    private const val PREFS = "demo" // excluded from backup, see res/xml/*_rules.xml
    private const val KEY = "test_user_id"

    fun get(context: Context): String? =
        if (BuildConfig.DEBUG) prefs(context).getString(KEY, null) else null

    fun set(context: Context, userId: String) {
        // Written synchronously: the app may restart right after this.
        prefs(context).edit(commit = true) { putString(KEY, userId) }
    }

    private fun prefs(context: Context) = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
}
