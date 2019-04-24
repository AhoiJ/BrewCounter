package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_total_mode.*

class TotalStatisticsActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    var smallBeer: Int = 0
    var largeBeer: Int = 0
    var longDrink: Int = 0
    var cider: Int = 0
    var wineGlass: Int = 0
    var shot: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_statistics)

        loadDrinks()
    }

    fun updateList() {
        tvSbAmount.setText("" + smallBeer)
        tvLbAmount.setText("" + largeBeer)
        tvLdAmount.setText("" + longDrink)
        tvCiderAmount.setText("" + cider)
        tvWgAmount.setText("" + wineGlass)
        tvShotAmount.setText("" + shot)
    }

    fun loadDrinks() {
        val sharPref = getSharedPreferences("DrinksData", Context.MODE_PRIVATE)

        smallBeer = sharPref.getInt("smallBeers", smallBeer)
        largeBeer = sharPref.getInt("largeBeers", largeBeer)
        longDrink = sharPref.getInt("longDrinks", longDrink)
        cider = sharPref.getInt("ciders", cider)
        wineGlass = sharPref.getInt("wineGlasses", wineGlass)
        shot = sharPref.getInt("shots", shot)
        updateList()
    }
}
