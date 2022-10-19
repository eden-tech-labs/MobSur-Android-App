package com.example.mobsurandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.edentechlabs.survey.sdk.MobSur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobSur.setup(applicationContext, "686034d7-dbf2-4555-8c7b-7ccfdc84927a", "userId")
        MobSur.setFragmentManager(supportFragmentManager)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            MobSur.event("myevent")
        }
    }
}