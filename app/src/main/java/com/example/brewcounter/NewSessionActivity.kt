package com.example.brewcounter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_start_session.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class NewSessionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_session)

        mBtnSubmit.setOnClickListener(View.OnClickListener {
            var newSession = setSession()
            var check = newSession.id
            if (check != 0) {

                // saves session to sharedpreferences
                saveSessionToArray(newSession)

                val intent = Intent(this, SessionMode::class.java)
                startActivity(intent)
                // un-ables user to use back button to return to this activity
                finish()
            } else {
                val toast = Toast.makeText(this, "Time must be\n8 to 24 hours!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 200)
                toast.show()
            }
        })


    }

    fun setSession(): sessions {
        // getLatestId gets latest session id to make a new one
        var id = getNewId() // not tested 19.4

        val titleName = etvInputTitle.text.toString()
        val sessionLength = etvTime.text.toString().toInt() // limit session length to 8-24 hours
        if (checkTimeValidity(sessionLength) == 1) {
            var curtime = checkTime()
            // needs to get last session id to update id num
            val newSession = sessions(id, titleName, sessionLength.toString(), curtime, 0, 0,0,0,0,0)
            // Make list or array of session class to send newSession
            return newSession
        } else {
            var emptySession = sessions(0, "", "", "", 0, 0,0,0,0,0)
            return emptySession
        }
    }


    // Checks what id´s are in use to get a new one
    fun getNewId(): Int {
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
        storeLatest++
        return storeLatest
    }
    // saves session to array for later use
    fun saveSessionToArray(newSession: sessions) {
        val sharedPreference = getSharedPreferences("SessionData", 0)
        var editor = sharedPreference.edit()
        var gson = Gson()
        var json = gson.toJson(newSession)
        editor.putString(newSession.id.toString(), json)
        editor.apply()
    }

    fun checkTimeValidity(time: Int): Int {
        var tick: Int = 0
        for (i in 8..24) {
            if (time == i)
                tick++
        }
        return tick
    }

    fun checkTime(): String {
        val sdf = SimpleDateFormat(
            "MM-dd HH:mm:ss",
            Locale.getDefault()
        )
        sdf.timeZone = TimeZone.getTimeZone("Etc/GMT-3")  // sets time to finnish time
        val currentDate = sdf.format(Date())
        return currentDate
    }

}
