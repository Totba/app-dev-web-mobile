package com.example.velo_nantes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.velo_nantes.R
import com.example.velo_nantes.model.Station
import com.example.velo_nantes.model.currentLocation
import com.example.velo_nantes.model.stationSelected
import com.example.velo_nantes.ui.station.StationMapsActivity

class StationAdapter(private val stations:List<Station>, private val context: Context) :
    RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.findViewById(R.id.cardView)
        var name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val status : ImageView = itemView.findViewById(R.id.status)
        val availability : TextView = itemView.findViewById((R.id.availability))
        val distance : TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item,parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.name.text = station.name

        if(currentLocation != null){
            holder.distance.text = "${String.format("%.2f",currentLocation!!.distanceTo(station.toLocation())/1000)} Km"
        } else {
            holder.distance.text = " - km"
        }


        holder.address.text = station.address
        holder.availability.text = " 🚲${station.availableBikes}📣${station.availableBikeStands}✅${station.bikeStands} "
        if(station.availableBikes.toInt() == 0){
            holder.name.setTextColor(context.getColor(R.color.empty_bike))
        } else {
            holder.name.setTextColor(context.getColor(R.color.black))
        }
        if ("OPEN" == station.status) {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_checked_24)
        } else {
            holder.status.setImageResource(R.drawable.ic_baseline_radio_button_unchecked_24)
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, StationMapsActivity::class.java)
            stationSelected = station

            context.startActivity(intent)
        }
    }

    // On retroune le nombre d'élément de la liste des stations
    override fun getItemCount(): Int {
        return stations.size
    }
}