package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_session_history.*
import android.widget.Toast
import android.widget.TextView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener


class SessionHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_history)
        // gets all sessions to map (? Not yet tested 25.4)
        var sessionListMap = getSessions()

        var counter = getLatestId()

        val listSessions = arrayOfNulls<String>(counter.toInt())
        val listSessionIds = arrayOfNulls<Int>(counter.toInt())

        for (i in 0 until counter) {
            var id = sessionListMap["id"].toString()
            listSessionIds[i] = id.toDouble().toInt()
            listSessions[i] = sessionListMap["title"].toString()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listSessions)
        sessionView.adapter = adapter

        sessionView.setOnItemClickListener { parent, view, position, id ->
            var id = listSessionIds[position]!!.toInt()
            displaySessionInfo(id)

            Toast.makeText(this, listSessions[position], Toast.LENGTH_LONG).show()
        }
    }


    fun getSessions(): Map<String, Any> {
        var id = getLatestId() // gets latest id in list
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        var gson = Gson() // initialize gson for gson.fromJson
        var json = sharedPreference.getString(id.toString(), null) // get data of latest session
        var sessionMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
        id--
        // pushing all sessions to map at the same time may not work !!!
        while (id != 0) {
            json = sharedPreference.getString(id.toString(), null) // get data of latest session
            sessionMap = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
            id--
        }
        return sessionMap
    }

    fun displaySessionInfo(pos: Int){
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        var gson = Gson() // initialize gson for gson.fromJson
        var json = sharedPreference.getString(pos.toString(), null) // get data of latest session
        var sessionMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)

        // Gets data by key from map
        var curtime = sessionMap["curTime"].toString()
        var idAsString = sessionMap["id"].toString()
        var largeBeerAsString = sessionMap["largeBeer"].toString()
        var sessionLength = sessionMap["sessionLength"].toString()
        var smallBeerAsString = sessionMap["smallBeer"].toString()
        var longDrinkAsString = sessionMap["longDrink"].toString()
        var ciderAsString = sessionMap["cider"].toString()
        var wineGlassAsString = sessionMap["wineGlass"].toString()
        var shotAsString = sessionMap["shot"].toString()
        var title = sessionMap["title"].toString()

        // Make Int values be int
        var newId = (idAsString.toDouble()).toInt()
        var largeBeer = (largeBeerAsString.toDouble()).toInt()
        var smallBeer = (smallBeerAsString.toDouble()).toInt()
        var longDrink = (longDrinkAsString.toDouble().toInt())
        var cider = (ciderAsString.toDouble().toInt())
        var wineGlass = (wineGlassAsString.toDouble().toInt())
        var shot = (shotAsString.toDouble().toInt())

        // Add data to session which will be returned
        var session = sessions(
            newId, title, sessionLength, curtime, smallBeer, largeBeer,
            longDrink, cider, wineGlass, shot
        )



    }
/*
    fun loadSessionData(): sessions {

        var id = getLatestId() // gets latest id in list
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        var gson = Gson() // initialize gson for gson.fromJson
        var json = sharedPreference.getString(id.toString(), null) // get data of latest session

        // Maps sessions so data can be accessed
        var sessionMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)
        // Gets data by key from map
        var curtime = sessionMap["curTime"].toString()
        var idAsString = sessionMap["id"].toString()
        var largeBeerAsString = sessionMap["largeBeer"].toString()
        var sessionLength = sessionMap["sessionLength"].toString()
        var smallBeerAsString = sessionMap["smallBeer"].toString()
        var longDrinkAsString = sessionMap["longDrink"].toString()
        var ciderAsString = sessionMap["cider"].toString()
        var wineGlassAsString = sessionMap["wineGlass"].toString()
        var shotAsString = sessionMap["shot"].toString()
        var title = sessionMap["title"].toString()

        // Make Int values be int
        var newId = (idAsString.toDouble()).toInt()
        var largeBeer = (largeBeerAsString.toDouble()).toInt()
        var smallBeer = (smallBeerAsString.toDouble()).toInt()
        var longDrink = (longDrinkAsString.toDouble().toInt())
        var cider = (ciderAsString.toDouble().toInt())
        var wineGlass = (wineGlassAsString.toDouble().toInt())
        var shot = (shotAsString.toDouble().toInt())

        // Add data to session which will be returned
        var session = sessions(
            newId, title, sessionLength, curtime, smallBeer, largeBeer,
            longDrink, cider, wineGlass, shot
        )

        return session // returns session for use
    }
*/
    fun getLatestId(): Int {
        var id: Int = 1
        var storeLatest: Int = 0
        var bool: Boolean = true
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        while (bool) {
            if (sharedPreference.contains(id.toString())) {
                storeLatest = id
                id++
            } else bool = false
        }
        return storeLatest
    }

}
