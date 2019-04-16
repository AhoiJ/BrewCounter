package com.example.brewcounter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start_session.*
import java.text.SimpleDateFormat
import java.util.*

class NewSessionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_session)

        mBtnSubmit.setOnClickListener(View.OnClickListener {
            if (setSession()) {
                // Needs to send newSession data to Array or List, make them!
                val intent = Intent(this, SessionMode::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this, "Time must be\n8 to 24 hours!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP, 0, 200)
                toast.show()
            }
        })


    }

    fun setSession(): Boolean {
        val titleName = etvInputTitle.text.toString()
        val sessionLength = etvTime.text.toString().toInt() // limit session length to 8-24 hours
        if (checkTimeValidity(sessionLength) == 1) {
            var curtime = checkTime()
            val newSession = sessions(1, titleName, sessionLength.toString(), curtime, 0, 0)
            // Make list or array of session class to send newSession
            return true
        } else
            return false

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
            "HH:mm:ss",
            Locale.getDefault()
        )
        sdf.timeZone = TimeZone.getTimeZone("Etc/GMT-3")  // sets time to finnish time
        val currentDate = sdf.format(Date())
        return currentDate
    }
}
