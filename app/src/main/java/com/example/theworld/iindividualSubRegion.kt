package com.example.theworld

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.Adapter.CountriesAdapter
import com.example.theworld.Adapter.IndividualSubRegionAdapter
import com.example.theworld.Data.Country
import com.example.theworld.Data.Flag
import com.example.theworld.Data.Srcountry
import com.example.theworld.Data.Srflag
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class iindividualSubRegion : AppCompatActivity() {
lateinit var name : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iindividual_sub_region)
        val srname1 = intent.getStringExtra("srname1")


        supportActionBar?.hide()

        var countries = arrayListOf<Srcountry>()

        // selecting rv
        val rvScontainer = findViewById<RecyclerView>(R.id.rvScontainer)

        // layout manager
        rvScontainer.layoutManager = LinearLayoutManager(this@iindividualSubRegion)

        // setting adapter
        rvScontainer.adapter = IndividualSubRegionAdapter(countries)


        val client = OkHttpClient()


        val request = Request.Builder()
            .url("https://restcountries.com/v3.1/subregion/$srname1")
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", e.message.toString())
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call, response: Response) {
                if(response.body() != null) {
                    val jsonArray = JSONArray(response.body()!!.string())

                    for(i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)

                        name = jsonObject.getJSONObject("name").getString("common")
                        val srIndicator = findViewById<TextView>(R.id.srIndicator)

                        var capital = ""
                        var description = ""
                        val region = jsonObject.getString("continents").replace("[","").replace("]","").replace("\"","").replace(",",", ")
                        val population = jsonObject.getString("population")

                        if(jsonObject.has("capital")) {
                            capital = jsonObject.getJSONArray("capital").getString(0)
                        }


                        val flagUrl = jsonObject.getJSONObject("flags").getString("png")

                        if(jsonObject.getJSONObject("flags").has("alt"))
                        {
                            description = jsonObject.getJSONObject("flags").getString("alt")
                        }

                        val newCountry = Srcountry(
                            name = name,
                            capital = capital,
                            region = region,
                            population = population,
                            flag = Srflag(
                                url= flagUrl,
                                description = description
                            )

                        )

                        countries.add(newCountry)


                        runOnUiThread {
                            rvScontainer.adapter?.notifyDataSetChanged()
                            srIndicator.setText(jsonObject.getString("subregion"))
                        }

                    }
                }
            }
        })

    }}

