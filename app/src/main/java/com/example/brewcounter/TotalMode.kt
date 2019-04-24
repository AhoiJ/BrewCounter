package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.brewcounter.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class TotalMode : AppCompatActivity() {

    private lateinit var listView: ListView

    var smallBeer: Int = 0
    var largeBeer: Int = 0

    val strSB = "Small Beer 0,33L  "
    val strLB = "Large Beers 0,5L  "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_mode)

        updateList()

        saveDrinks(smallBeer, largeBeer)


    }

    fun updateList(){

        loadDrinks()

        listView = findViewById<ListView>(R.id.totalDrinkList)

        val listItems = arrayOf<String>(strSB + smallBeer, strLB + largeBeer)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

    }

    fun loadDrinks(){
        val sharPref = getSharedPreferences("DrinksData", Context.MODE_PRIVATE)

        smallBeer = sharPref.getInt("smallBeers", smallBeer)
        largeBeer = sharPref.getInt("largeBeers", largeBeer)
    }

    fun saveDrinks(sB: Int, lB : Int){
        val sharPref = getSharedPreferences("DrinksData", 0)
        var editor = sharPref.edit()

        editor.putInt("smallBeers", sB)
        editor.putInt("largeBeers", lB)
        editor.apply()
    }

}
