package com.example.brewcounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SessionMode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_mode)

        // get newest session from array (not yet implemented)
       val sessionArray: Array <sessions> = arrayOf()

    }

}
