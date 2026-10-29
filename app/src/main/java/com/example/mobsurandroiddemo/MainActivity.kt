package com.example.mobsurandroiddemo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.edentechlabs.survey.sdk.MobSur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The SDK shows surveys in a bottom sheet through this FragmentManager (held weakly).
        MobSur.setFragmentManager(supportFragmentManager)

        findViewById<TextView>(R.id.sdkVersion).text = getString(R.string.sdk_version, MobSur.VERSION)
        findViewById<Button>(R.id.button).setOnClickListener {
            MobSur.event("MY_Event")
        }
    }
}
