package com.example.brewcounter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SessionMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_mode)

        // get newest session from array
        var currentSession = loadSessionData()

        // as a test to see if data is correct
        textView.setText(currentSession.id.toString())
        textView2.setText(currentSession.title)

    }

    fun loadSessionData(): sessions {

        var id = getLatestId() // gets latest id in list
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
    }

    fun getLatestId(): Int {
        var id: Int = 1
        var storeLatest: Int = 0
        var bool: Boolean = true
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        while (bool) {
            if (sharedPreference.contains(id.toString())) {
                storeLatest = id
                id++
            } else bool = false
        }
        return storeLatest
    }

}
