package com.example.brewcounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.SimpleDateFormat
import java.util.*


class SessionMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_mode)

        // on startup get current time and check it
        // against the time when previous session was supposed to end
        val currentTime:String
        currentTime = checkTime()
        teksti.setText(currentTime)
        // if previous session has not ended, open that session
        // else open "questioneer" for new session


    }

    fun checkTime():String{
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
    }

}
