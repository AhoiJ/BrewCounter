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
import java.util.*

class TotalStatisticsActivity : AppCompatActivity() {

    var smallBeer: Int = 0
    var largeBeer: Int = 0
    var longDrink: Int = 0
    var cider: Int = 0
    var wineGlass: Int = 0
    var shot: Int = 0

    var sBPrice:String = ""
    var lBPrice:String = ""
    var lDPrice:String = ""
    var ciderPrice:String = ""
    var wGPrice:String = ""
    var shotsPrice:String = ""

    var sBTotalPrice:Double = 0.0
    var lBTotalPrice:Double = 0.0
    var lDTotalPrice:Double = 0.0
    var ciderTotalPrice:Double = 0.0
    var wGTotalPrice:Double = 0.0
    var shotsTotalPrice:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_statistics)

        loadDrinks() // loads values to drink variables
        loadPrices() // loads values to price variables
        priceCalculator() // calculates price from variables
        updateTotals() // updates UI
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

        val prices = getSharedPreferences("prices", Context.MODE_PRIVATE)
        sBPrice = prices.getString("smallBeer", sBPrice)
        lBPrice = prices.getString("largeBeer", lBPrice)
        lDPrice = prices.getString("longDrink", lDPrice)
        ciderPrice = prices.getString("cider", ciderPrice)
        wGPrice = prices.getString("wineGlass", wGPrice)
        shotsPrice = prices.getString("shots", shotsPrice)
    }

    // Checks if variable is empty
    fun checkIfEmpty(checkThis: String): Boolean {
        if (checkThis == "") {
            return true
        } else
            return false
    }

    fun priceCalculator() {

        if(!checkIfEmpty(sBPrice)) {
            sBTotalPrice = sBPrice.toDouble() * smallBeer.toDouble()
        }else
            sBTotalPrice = 0.0

        if(!checkIfEmpty(lBPrice)) {
            lBTotalPrice = lBPrice.toDouble() * largeBeer
        }else
            lBTotalPrice = 0.0

        if(!checkIfEmpty(lDPrice)) {
            lDTotalPrice = lDPrice.toDouble() * longDrink
        }else
            lDTotalPrice = 0.0

        if(!checkIfEmpty(ciderPrice)) {
            ciderTotalPrice = ciderPrice.toDouble() * cider
        }else
            ciderTotalPrice = 0.0

        if(!checkIfEmpty(wGPrice)) {
            wGTotalPrice = wGPrice.toDouble() * wineGlass
        }else
            wGTotalPrice = 0.0

        if(!checkIfEmpty(shotsPrice)) {
            shotsTotalPrice = shotsPrice.toDouble() * shot
        }else
            shotsTotalPrice = 0.0
    }

    fun updateTotals() {

        tvSbTotal.setText("" +  "%.2f".format(sBTotalPrice).toDouble()  + "€")
        tvLbTotal.setText("" + "%.2f".format(lBTotalPrice).toDouble() + "€")
        tvLdTotal.setText("" +   "%.2f".format(lDTotalPrice).toDouble()  + "€")
        tvCiderTotal.setText("" +   "%.2f".format(ciderTotalPrice).toDouble()  + "€")
        tvWgTotal.setText("" +   "%.2f".format(wGTotalPrice).toDouble()  + "€")
        tvShotTotal.setText("" +   "%.2f".format(shotsTotalPrice).toDouble()  + "€")
    }

}
