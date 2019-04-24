package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_total_mode.*


class TotalMode : AppCompatActivity() {

    private lateinit var listView: ListView

    var smallBeer: Int = 0
    var largeBeer: Int = 0
    var longDrink: Int = 0
    var cider: Int = 0
    var wineGlass: Int = 0
    var shot: Int = 0

    val strSB = "Small Beer 0,33L  "
    val strLB = "Large Beer 0,5L    "
    val strLD = "Long Drink            "
    val strC = "Cider                          "
    val strWG = "Wine Glass           "
    val strS = "Shots                      "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_mode)

        loadDrinks()

        iBtnSubtractSb.setOnClickListener{
            smallBeer--
            tvSbAmount.setText("" + smallBeer)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnAddSb.setOnClickListener{
            smallBeer++
            tvSbAmount.setText("" + smallBeer)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnSubtractLb.setOnClickListener{
            largeBeer--
            tvLbAmount.setText("" + largeBeer)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnAddLb.setOnClickListener{
           largeBeer++
            tvLbAmount.setText("" + largeBeer)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)

        }

        iBtnSubtractLd.setOnClickListener{
            longDrink--
            tvSbAmount.setText("" + longDrink)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnAddLd.setOnClickListener{
            longDrink++
            tvSbAmount.setText("" + longDrink)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnSubtractC.setOnClickListener{
            cider--
            tvSbAmount.setText("" + cider)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnAddC.setOnClickListener{
            cider++
            tvSbAmount.setText("" + cider)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnSubtractWg.setOnClickListener{
            wineGlass--
            tvSbAmount.setText("" + wineGlass)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnAddWg.setOnClickListener{
            wineGlass++
            tvSbAmount.setText("" + wineGlass)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnSubtractS.setOnClickListener{
            shot--
            tvSbAmount.setText("" + shot)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

        iBtnSubtractS.setOnClickListener{
            shot++
            tvSbAmount.setText("" + shot)
            saveDrinks(smallBeer, largeBeer, longDrink, cider, wineGlass, shot)
        }

    }

    fun updateList(){
        tvSbAmount.setText("" + smallBeer)
        tvLbAmount.setText("" + largeBeer)
    }

    fun loadDrinks(){
        val sharPref = getSharedPreferences("DrinksData", Context.MODE_PRIVATE)

        smallBeer = sharPref.getInt("smallBeers", smallBeer)
        largeBeer = sharPref.getInt("largeBeers", largeBeer)
        longDrink = sharPref.getInt("longDrinks", longDrink)
        cider = sharPref.getInt("ciders", cider)
        wineGlass = sharPref.getInt("wineGlasses", wineGlass)
        shot = sharPref.getInt("shots", shot)
        updateList()
    }

    fun saveDrinks(sB: Int, lB : Int, lD : Int, C : Int, wG : Int, S : Int){
        val sharPref = getSharedPreferences("DrinksData", 0)
        var editor = sharPref.edit()

        editor.putInt("smallBeers", sB)
        editor.putInt("largeBeers", lB)
        editor.putInt("longDrinks", lD)
        editor.putInt("ciders", C)
        editor.putInt("wineGlasses", wG)
        editor.putInt("shots", S)
        editor.apply()
    }

    /*
            listView = findViewById<ListView>(R.id.totalDrinkList)
        val listItems = arrayOf<String>(strSB + smallBeer,"", strLB + largeBeer,"", strLD + longDrink,"", strC + cider,"", strWG + wineGlass,"", strS + shot)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
     */

}
