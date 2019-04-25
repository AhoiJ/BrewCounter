package com.example.brewcounter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_session_mode.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class SessionMode : AppCompatActivity() {

    var smallBeer: Int = 0
    var largeBeer: Int = 0
    var longDrink: Int = 0
    var cider: Int = 0
    var wineGlass: Int = 0
    var shot: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_mode)

        // get newest session from array
        var currentSession = loadSessionData()
        // Loads values for drinks from "totalList"
        loadTotalVals()

        // used to add sessionLength to sessionStart time
        var sdf = SimpleDateFormat("MM-dd HH:mm:ss")
        var date: Date = sdf.parse(currentSession.curTime) // fault here
        var calendar: Calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.HOUR, currentSession.sessionLength.toInt())
        var endTime = sdf.format(calendar.getTime())

        // shows title, start and end times
        tvTitle.setText("" + currentSession.title)
        tvSessionEndTime.setText("Session end time:\n" + endTime)
        tvSessionStartTime.setText("Session started:\n" + currentSession.curTime)

        setDrinks(currentSession)

        iBtnAddSbSes.setOnClickListener {
            currentSession.smallBeer++
            smallBeer++
            setDrinks(currentSession)
        }
        iBtnSubtractSbSes.setOnClickListener {
            // subtract small beer
            currentSession.smallBeer--
            smallBeer--
            setDrinks(currentSession)
        }
        iBtnAddLbSes.setOnClickListener {
            // add large beer
            currentSession.largeBeer++
            largeBeer++
            setDrinks(currentSession)
        }
        iBtnSubtractLbSes.setOnClickListener {
            // subtract large beer
            currentSession.largeBeer--
            largeBeer--
            setDrinks(currentSession)
        }
        iBtnAddLdSes.setOnClickListener {
            // add LongDrink
            currentSession.longDrink++
            longDrink++
            setDrinks(currentSession)
        }
        iBtnSubtractLdSes.setOnClickListener {
            // subtract LongDrink
            currentSession.longDrink--
            longDrink--
            setDrinks(currentSession)
        }
        iBtnAddCiderSes.setOnClickListener {
            // add cider
            currentSession.cider++
            cider++
            setDrinks(currentSession)
        }
        iBtnSubtractCiderSes.setOnClickListener {
            // subtract cider
            currentSession.cider--
            cider--
            setDrinks(currentSession)
        }
        iBtnAddWGSes.setOnClickListener {
            // add wineGlass
            currentSession.wineGlass++
            wineGlass++
            setDrinks(currentSession)
        }
        iBtnSubtractWGSes.setOnClickListener {
            // subtract wineGlass
            currentSession.wineGlass--
            wineGlass--
            setDrinks(currentSession)
        }
        iBtnAddShotSes.setOnClickListener {
            // add shot
            currentSession.shot++
            shot++
            setDrinks(currentSession)
        }
        iBtnSubtractShotSes.setOnClickListener {
            // subtract shot
            currentSession.shot--
            shot--
            setDrinks(currentSession)
        }

    }

    fun setDrinks(session: sessions) {

        tvSbAmountSes.setText("" + session.smallBeer)
        tvLbAmountSes.setText("" + session.largeBeer)
        tvLdAmountSes.setText("" + session.longDrink)
        tvCiderAmountSes.setText("" + session.cider)
        tvWgAmountSes.setText("" + session.wineGlass)
        tvShotAmountSes.setText("" + session.shot)

        saveSession(session)
        saveSessionToTotal(session)
    }

    fun saveSession(session: sessions) {

        val sharedPreference = getSharedPreferences("SessionData", 0)
        var editor = sharedPreference.edit()
        var gson = Gson()
        var json = gson.toJson(session)
        editor.putString(session.id.toString(), json)
        editor.apply()

    }

    // adds drinks added to session into totalMode
    fun saveSessionToTotal(session: sessions) {

        val sharPref = getSharedPreferences("DrinksData", 0)
        var editor = sharPref.edit()
        editor.putInt("smallBeers", smallBeer)
        editor.putInt("largeBeers", largeBeer)
        editor.putInt("longDrinks", longDrink)
        editor.putInt("ciders", cider)
        editor.putInt("wineGlasses", wineGlass)
        editor.putInt("shots", shot)
        editor.apply()

    }

    // Loads totalMode values so session values can be added to them
    fun loadTotalVals() {
        val sharPref = getSharedPreferences("DrinksData", Context.MODE_PRIVATE)

        smallBeer = sharPref.getInt("smallBeers", smallBeer)
        largeBeer = sharPref.getInt("largeBeers", largeBeer)
        longDrink = sharPref.getInt("longDrinks", longDrink)
        cider = sharPref.getInt("ciders", cider)
        wineGlass = sharPref.getInt("wineGlasses", wineGlass)
        shot = sharPref.getInt("shots", shot)
    }

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
