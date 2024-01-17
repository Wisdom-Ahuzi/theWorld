package com.example.theworld.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.AboutCountry
import com.example.theworld.Data.Country
import com.example.theworld.MainActivity
import com.example.theworld.R
import com.squareup.picasso.Picasso

class CountriesAdapter (private val countries:ArrayList<Country>, private val fragmentManager: FragmentManager) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(){
    inner class CountriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
        val capitalView: TextView = itemView.findViewById(R.id.capital)
        val populationView: TextView = itemView.findViewById(R.id.population)
        val regionView: TextView = itemView.findViewById(R.id.region)
        val flagImageView: ImageView = itemView.findViewById(R.id.flag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country, parent,false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countries[position]
        holder.nameView.text = country.name
        holder.capitalView.text = country.capital
        holder.regionView.text = country.region
        holder.populationView.text = country.population
//        holder.flagImageView.setImageResource(country.flag)

        Picasso.get().load(country.flag.url).into(holder.flagImageView)
        holder.flagImageView.contentDescription = country.flag.description

        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, AboutCountry::class.java)
            i.putExtra("name",country.name)
            holder.itemView.context.startActivity(i)
        }
    }


    override fun getItemCount(): Int {
       return countries.size
    }


}