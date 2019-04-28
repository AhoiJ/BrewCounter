package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.tvSbTotalPrice
import kotlinx.android.synthetic.main.activity_total_mode.*
import kotlinx.android.synthetic.main.activity_total_mode.tvCiderAmount
import kotlinx.android.synthetic.main.activity_total_mode.tvLbAmount
import kotlinx.android.synthetic.main.activity_total_mode.tvLdAmount
import kotlinx.android.synthetic.main.activity_total_mode.tvSbAmount
import kotlinx.android.synthetic.main.activity_total_mode.tvShotAmount
import kotlinx.android.synthetic.main.activity_total_mode.tvWgAmount
import kotlinx.android.synthetic.main.activity_total_statistics.*

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
        priceCalculator()
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

    fun loadPrices() {
        val sharedPrice = getSharedPreferences("prices", Context.MODE_PRIVATE)
        // variables need to be declared above override
        sBPrice = prices.getString("smallBeer", sBPrice)
        lBPrice = prices.getString("largeBeer", lBPrice)
        lDPrice = prices.getString("longDrink", lDPrice)
        ciderPrice = prices.getString("cider", ciderPrice)
        wGPrice = prices.getString("wineGlass", wGPrice)
        shotsPrice = prices.getString("shots", shotsPrice)


    }
}
