package com.example.brewcounter

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var sBPrice:String = ""
        var lBPrice:String = ""
        var lDPrice:String = ""
        var ciderPrice:String = ""
        var wGPrice:String = ""
        var shotsPrice:String = ""


        val prices = getSharedPreferences("prices", 0)
        sBPrice = prices.getString("smallBeer", sBPrice)
        lBPrice = prices.getString("largeBeer", lBPrice)
        lDPrice = prices.getString("longDrink", lDPrice)
        ciderPrice = prices.getString("cider", ciderPrice)
        wGPrice = prices.getString("wineGlass", wGPrice)
        shotsPrice = prices.getString("shots", shotsPrice)


        BtnSubmitDrinks.setOnClickListener {
            sBPrice = tvSbPrice.getText().toString()
            lBPrice = tvLbPrice.getText().toString()
            lDPrice = tvLdPrice.getText().toString()
            ciderPrice = tvCiderPrice.getText().toString()
            wGPrice = tvWgPrice.getText().toString()
            shotsPrice = tvShotPrice.getText().toString()

            val prices = getSharedPreferences("prices", 0)
            val editor = prices.edit()
            editor.putString("smallBeer", sBPrice)
            editor.putString("largeBeer", lBPrice)
            editor.putString("longDrink", lDPrice)
            editor.putString("cider", ciderPrice)
            editor.putString("shots", shotsPrice)
            editor.commit()

            displayDrinks(sBPrice, lBPrice, lDPrice, ciderPrice, wGPrice, shotsPrice )
        }
    }
    fun displayDrinks(smallBeer: String, largeBeer: String, longDrink: String, cider: String, wineGlass: String, shot: String){

        tvSbTotalPrice.setText("" + smallBeer + "€")
    }

}
