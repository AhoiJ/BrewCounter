package com.example.brewcounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_session_history.*
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.session_info_popup_layout.*
import kotlinx.android.synthetic.main.session_info_popup_layout.view.*


class SessionHistoryActivity : AppCompatActivity() {

    var sBPrice: String = ""
    var lBPrice: String = ""
    var lDPrice: String = ""
    var ciderPrice: String = ""
    var wGPrice: String = ""
    var shotsPrice: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_history)

        var counter = getLatestId()
        val listSessions = arrayOfNulls<String>(counter)
        val listSessionIds = arrayOfNulls<Int>(counter)

        for (i in 0 until counter) {
            var sessionList = getSessions(i + 1)
            var id = sessionList["id"].toString()
            listSessionIds[i] = id.toDouble().toInt()
            listSessions[i] = sessionList["title"].toString()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listSessions)
        sessionView.adapter = adapter

        // opens correct session and displays data in popup window
        sessionView.setOnItemClickListener { parent, view, position, id ->

            var id = listSessionIds[position]!!.toInt()
            var dispSession = displaySessionInfo(id)

            // get total cost of session
            var cost = sessionCost(dispSession)
            val dispCost = "%.2f".format(cost) + "€"
            // Initialize popup window
            val popup = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.session_info_popup_layout, null)
            popup.contentView = view
            popup.showAtLocation(sessionView, Gravity.CENTER, 0, 0)

            // sets textviews in popup to display beer values
            view.tvTitleShow.setText("" + dispSession.title)
            view.tvSbShowAmount.setText("" + dispSession.smallBeer)
            view.tvLbShowAmount.setText("" + dispSession.largeBeer)
            view.tvLdShowAmount.setText("" + dispSession.longDrink)
            view.tvCiderShowAmount.setText("" + dispSession.cider)
            view.tvWgShowAmount.setText("" + dispSession.wineGlass)
            view.tvShotsShowAmount.setText("" + dispSession.shot)
            view.tvCostShowAmount.setText("" + dispCost)

            // onClickListener to close popup windwo
            val dismissButton = view.findViewById<Button>(R.id.mBtnDismiss)
            dismissButton.setOnClickListener {
                popup.dismiss()
            }
        }
    }

    fun sessionCost(sessionInfo: sessions): Double {

        loadPrices()
        var cost: Double = 0.0

        if (sBPrice != "" )
            cost = sBPrice.toDouble() * sessionInfo.smallBeer.toDouble()
        if (lBPrice != "")
            cost += lBPrice.toDouble() * sessionInfo.largeBeer.toDouble()
        if (lDPrice != "")
            cost += lDPrice.toDouble() * sessionInfo.longDrink.toDouble()
        if (ciderPrice != "")
            cost += ciderPrice.toDouble() * sessionInfo.cider.toDouble()
        if (wGPrice != "")
            cost += wGPrice.toDouble() * sessionInfo.wineGlass.toDouble()
        if (shotsPrice != "")
            cost += shotsPrice.toDouble() * sessionInfo.shot.toDouble()
        

        return cost
    }

    fun loadPrices() {

        val prices = getSharedPreferences("prices", 0)
        sBPrice = prices.getString("smallBeer", sBPrice)
        lBPrice = prices.getString("largeBeer", lBPrice)
        lDPrice = prices.getString("longDrink", lDPrice)
        ciderPrice = prices.getString("cider", ciderPrice)
        wGPrice = prices.getString("wineGlass", wGPrice)
        shotsPrice = prices.getString("shots", shotsPrice)
    }


    fun getSessions(count: Int): Map<String, Any> {
        val sharedPreference = getSharedPreferences("SessionData", Context.MODE_PRIVATE)
        var gson = Gson() // initialize gson for gson.fromJson
        // array to hold all sessions

        var json = sharedPreference.getString(count.toString(), null) // get data of latest session
        var sessionMap: Map<String, Any> = gson.fromJson(json, object : TypeToken<Map<String, Any>>() {}.type)

        return sessionMap
    }

    // Gets a session id based on its position in list where clicked
    fun displaySessionInfo(pos: Int): sessions {

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

        return session
    }

    // gets latest id used in session
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
/*
    fun saveSessionToArray(newSession: sessions) {
        val sharedPreference = getSharedPreferences("SessionData", 0)
        var editor = sharedPreference.edit()
        var gson = Gson()
        var json = gson.toJson(newSession)
        editor.putString(newSession.id.toString(), json)
        editor.apply()
    }
    */
}
