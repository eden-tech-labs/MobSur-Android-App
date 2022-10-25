package com.example.mobsurandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.edentechlabs.survey.sdk.MobSur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobSur.setup(applicationContext, "36121162-25d6-40f1-ab24-d5bd87a47601", "userId_1")
        MobSur.setFragmentManager(supportFragmentManager)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            MobSur.event("MY_Event")
        }
    }
}