package com.example.brewcounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_session.*

class startSessionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_session)

        setSession() // put this inside submit button listener

    }

    fun setSession (){
        val titleName = etvInputTitle.text.toString()
        val sessionLength = etvTime.text.toString()
    }
}
