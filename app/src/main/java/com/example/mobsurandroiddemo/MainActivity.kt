package com.example.mobsurandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.edentechlabs.survey.sdk.MobSur
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userId = Random.nextInt(from = 0, until = Int.MAX_VALUE)
        MobSur.setup(applicationContext, "36121162-25d6-40f1-ab24-d5bd87a47601", "$userId", debug = true)
        MobSur.setFragmentManager(supportFragmentManager)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            MobSur.event("MY_Event")
        }
    }
}