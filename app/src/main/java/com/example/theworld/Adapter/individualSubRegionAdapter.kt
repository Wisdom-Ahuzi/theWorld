package com.example.theworld.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.AboutCountry
import com.example.theworld.Data.Srcountry
import com.example.theworld.R
import com.squareup.picasso.Picasso


class IndividualSubRegionAdapter(private val subregions:ArrayList<Srcountry>):RecyclerView.Adapter<IndividualSubRegionAdapter.SubregionsViewHolder>(){

    inner class SubregionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val srnameView: TextView = itemView.findViewById(R.id.name)
        val srcapitalView: TextView = itemView.findViewById(R.id.capital)
        val srpopulationView: TextView = itemView.findViewById(R.id.population)
        val srregionView: TextView = itemView.findViewById(R.id.region)
        val srflagImageView: ImageView = itemView.findViewById(R.id.flag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubregionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country, parent,false)
        return SubregionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubregionsViewHolder, position: Int) {
        val country = subregions[position]
        holder.srnameView.text = country.name
        holder.srcapitalView.text = country.capital
        holder.srpopulationView.text = country.population
        holder.srregionView.text = country.region

        Picasso.get().load(country.flag.url).into(holder.srflagImageView)
        holder.srflagImageView.contentDescription = country.flag.description

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, AboutCountry::class.java)
            i.putExtra("name",country.name)
            holder.itemView.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return subregions.size
    }
}