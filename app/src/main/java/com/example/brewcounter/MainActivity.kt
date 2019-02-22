package com.example.brewcounter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSession.setOnClickListener {
            val intent = Intent(this, SessionMode::class.java)
            startActivity(intent)
        }
        btnTally.setOnClickListener {
            val intent = Intent(this, TotalMode::class.java)
            startActivity(intent)
        }
        btnStatistic.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}
