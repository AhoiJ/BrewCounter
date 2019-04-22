package com.example.brewcounter

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
            // against the time when previous session was supposed to end
            var currentTime = checkTime() // implement get function from list into checkTime function

            // gets latestSession for time checks
            var checkSessionTime = loadSessionData()
            if (checkSessionTime.id != 0) { // if no data found open NewSessionActivity
                var sessionLength = checkSessionTime.sessionLength
                var sessionStartTime = checkSessionTime.curTime

                // times changed to calendar for comparing
                var sdf = SimpleDateFormat("MM-dd HH:mm:ss")
                var date: Date = sdf.parse(sessionStartTime) // fault here
                var calendar: Calendar = Calendar.getInstance()
                calendar.setTime(date)
                calendar.add(Calendar.HOUR, sessionLength.toInt())

                // times changed to calendar for comparing
                var sdfCurrent = SimpleDateFormat("MM-dd HH:mm:ss")
                var currentDate: Date = sdfCurrent.parse(currentTime)
                var calendarCurrent: Calendar = Calendar.getInstance()
                calendarCurrent.setTime(currentDate)

                // if previous session has not ended, open that session
                // not checked if works correctly, current test was klo:14.48 22.4, need to test 11 hours later
                if (calendar > calendarCurrent) {
                    // opens sessionMode
                    val intent = Intent(this, SessionMode::class.java)
                    startActivity(intent)
                } else {  // else open StartSessionActivity for new session info input
                    // Opens NewSessionActivity
                    val intentCreate = Intent(this, NewSessionActivity::class.java)
                    startActivity(intentCreate)
                }

            } else {
                // Opens NewSessionActivity
                val intentCreate = Intent(this, NewSessionActivity::class.java)
                startActivity(intentCreate)

            }
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
            "MM-dd HH:mm:ss",
            Locale.getDefault()
        )
        sdf.timeZone = TimeZone.getTimeZone("Etc/GMT-3")  // sets time to finnish time
        val currentDate = sdf.format(Date())
        return currentDate
    }

    // loads Session data for checking if current has expired
    fun loadSessionData(): sessions {
        var id = getLatestId() // gets latest id in list
        if (id != 0) {
            val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
            var gson = Gson() // initialize gson for gson.fromJson
            var json = sharedPreference.getString(id.toString(), null) // get data of latest session

            // Maps sessions so data can be accessed
            var sessionMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
            // Gets data by key from map
            var curtime = sessionMap["curTime"].toString()
            var idAsString = sessionMap["id"].toString()
            var largeBeerAsString = sessionMap["largeBeer"].toString()
            var sessionLength = sessionMap["sessionLength"].toString()
            var smallBeerAsString = sessionMap["smallBeer"].toString()
            var title = sessionMap["title"].toString()

            // Make Int values be int
            var newId = (idAsString.toDouble()).toInt()
            var largeBeer = (largeBeerAsString.toDouble()).toInt()
            var smallBeer = (smallBeerAsString.toDouble()).toInt()
            // Add data to session which will be returned
            var session = sessions(newId, title, sessionLength, curtime, smallBeer, largeBeer)

            return session // returns session for use
        } else {
            var session = sessions(0, "", "", "", 0, 0)
            return session
        }
    }

    // gets id of latest session
    fun getLatestId(): Int {
        var id: Int = 1
        var storeLatest: Int = 0
        var bool: Boolean = true
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        if (sharedPreference != null) {
            while (bool) {
                if (sharedPreference.contains(id.toString())) {
                    storeLatest = id
                    id++
                } else bool = false
            }
            return storeLatest
        } else return storeLatest
    }
}

