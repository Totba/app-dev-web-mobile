package com.example.velo_nantes.ui.station

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.velo_nantes.R
import com.example.velo_nantes.model.stationSelected

class StationDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)

        val stationName = findViewById<TextView>(R.id.stationName)
        val buttonOpen = findViewById<Button>(R.id.buttonOpenMap)


        stationSelected?.let {station ->
            stationName.text = station.name

            buttonOpen.setOnClickListener{
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${station.latitude},${station.longitude}(${station.name})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}