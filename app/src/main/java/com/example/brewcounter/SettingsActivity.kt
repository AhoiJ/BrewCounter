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

        var sBPrice:Int = 0
        var lBPrice:Int = 0
        var lDPrice:Int = 0
        var CiderPrice:Int = 0
        var wGPrice:Int = 0
        var shotsPrice:Int = 0
        var sBInput:EditText = findViewById<EditText>(R.id.tvSbPrice)
        var lBInput:EditText = findViewById<EditText>(R.id.tvLbPrice)
        var lDInput:EditText = findViewById<EditText>(R.id.tvLdPrice)
        var CiderInput:EditText = findViewById<EditText>(R.id.tvCiderPrice)
        var wGImput:EditText
        var shotsInput:EditText = findViewById<EditText>(R.id.tvShotPrice)
        var submitButton: Button = findViewById<Button>(R.id.BtnSubmitDrinks)

        val prices = getSharedPreferences("prices", 0)
        sBPrice = prices.getInt("smallBeer", sBPrice)
        lBPrice = prices.getInt("largeBeer", lBPrice)
        lDPrice = prices.getInt("longDrink", lDPrice)
        CiderPrice = prices.getInt("Cider", CiderPrice)
        wGPrice = prices.getInt("wineGlass", wGPrice)
        shotsPrice = prices.getInt("shots", shotsPrice)


        submitButton.setOnClickListener {
            sBPrice = sBInput.getText().toString()
            lBPrice = lBInput.getText().toString()
            lDPrice = lDInput.getText().toString()
            CiderPrice = CiderInput.getText().toString()
            shotsPrice = shotsInput.getText().toString()
            val prices = getSharedPreferences("prices", 0)
            val editor = prices.edit()
            editor.putString("smallBeer", sBPrice)
            editor.putInt("largeBeer", lBPrice)
            editor.putInt("longDrink", lDPrice)
            editor.putInt("smallCider", CiderPrice)
            editor.putInt("shots", shotsPrice)
            editor.commit()
            displaySmallBeer(sBPrice)
            displayLargeBeer(largeBeerPrice)
            displayLongDrink(longDrinkPrice)
            displaySmallCider(smallCiderPrice)
            displayShots(shotsPrice)
        }
    }
    fun displaySmallBeer(number:String) {
        val displayInteger = findViewById(
            R.id.tvSbPrice)
        displayInteger.setText("" + number + " €")
    }
    fun displayLargeBeer(number:String) {
        val displayInteger = findViewById(
            R.id.costOfLbeer)
        displayInteger.setText("" + number + " €")
    }
    fun displayLongDrink(number:String) {
        val displayInteger = findViewById(
            R.id.costOfLdrink)
        displayInteger.setText("" + number + " €")
    }
    fun displaySmallCider(number:String) {
        val displayInteger = findViewById(
            R.id.costOfSciders)
        displayInteger.setText("" + number + " €")
    }
    fun displayShots(number:String) {
        val displayInteger = findViewById(
            R.id.costOfShots)
        displayInteger.setText("" + number + " €")
    }
    }
}
