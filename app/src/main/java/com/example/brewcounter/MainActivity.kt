package com.example.brewcounter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSession.setOnClickListener {
            // on button click get current time and check it
            // against the time when previous session was supposed to end, does not do it yet
            val currentTime: String // was used for tests
            currentTime = checkTime() // implement get function from list into checkTime function
            // if previous session has not ended, open that session
            // else open StartSessionActivity for new session info input

            // Opens NewSessionActivity
            val intentCreate = Intent(this, NewSessionActivity::class.java)
            startActivity(intentCreate)

            // opens sessionMode
            // val intent = Intent(this, SessionMode::class.java)
            //startActivity(intent)
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

    // Implement get method to check time from current session
    // And get sessionLength to check if session has expired
    fun checkTime(): String {
        val sdf = SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        )
        sdf.timeZone = TimeZone.getTimeZone("Etc/GMT-3")  // sets time to finnish time
        val currentDate = sdf.format(Date())
        return currentDate
    }
}
