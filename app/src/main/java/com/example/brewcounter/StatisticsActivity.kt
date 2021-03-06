package com.example.brewcounter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        btnTotalStats.setOnClickListener{
            val intent = Intent(this, TotalStatisticsActivity::class.java)
            startActivity(intent)
        }

        btnSessionHistory.setOnClickListener{
            val intent = Intent(this, SessionHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
