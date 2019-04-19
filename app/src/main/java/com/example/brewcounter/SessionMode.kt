package com.example.brewcounter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SessionMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_mode)

        // get newest session from array (not yet implemented)
        var currentSession = loadSessionData()

        currentSession.id

    }

    fun loadSessionData():sessions{
        var id = getLatestId()
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        var gson = Gson()
        var json = sharedPreference.getString(id.toString(), null)
        var type = object : TypeToken<ArrayList<sessions>>() {}.type
        val session: sessions
        session = gson.fromJson(json, type)

        return session
    }

    fun getLatestId(): Int {
        var id: Int = 1
        var storeLatest: Int = 0
        var bool: Boolean = true
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        //  val ed: SharedPreferences.Editor
        while (bool) {
            if (sharedPreference.contains(id.toString())) {
                storeLatest = id
                id++
            } else bool = false
        }
        storeLatest
        return storeLatest
    }

}
