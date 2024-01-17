package com.example.theworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.lang.Exception

class AboutCountry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_country)

        supportActionBar?.hide()

        val name = intent.getStringExtra("name")


        val nameView = findViewById<TextView>(R.id.name)
        val BarCountryName = findViewById<TextView>(R.id.BarCountryName)
        val capitalView = findViewById<TextView>(R.id.capital)
        val flagView = findViewById<ImageView>(R.id.flag)
        val nativeNameView = findViewById<TextView>(R.id.nativeName)
        val officialView = findViewById<TextView>(R.id.officialName)
        val regionView = findViewById<TextView>(R.id.region)
        val subRegionView = findViewById<TextView>(R.id.subRegion)
        val continentView = findViewById<TextView>(R.id.continent)
        val bordersView = findViewById<TextView>(R.id.borders)
        val languageView = findViewById<TextView>(R.id.languages)
        val populationView = findViewById<TextView>(R.id.population)
        val coatView = findViewById<ImageView>(R.id.coat)
        val landlockedView = findViewById<TextView>(R.id.landlocked)
        val areaView = findViewById<TextView>(R.id.area)
        val timezonesView = findViewById<TextView>(R.id.timezones)
        val independentView = findViewById<TextView>(R.id.independent)
        val unMemberView = findViewById<TextView>(R.id.unMember)
        val abbreviationView = findViewById<TextView>(R.id.abbreviation)

        val currencyView = findViewById<TextView>(R.id.currency)

        val okHttpClient = OkHttpClient()



        val request = Request.Builder()
            .url("https://restcountries.com/v3.1/name/$name")
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
             override fun onFailure(call: Call, e: IOException) {
                Log.e("error", e.message.toString())
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                if(response.body()!= null) {
                    val jsonArray = JSONArray(response.body()!!.string())

                    val countryObject = jsonArray.getJSONObject(0)

                    val name = countryObject.getJSONObject("name").getString("common")
                    val officialName = countryObject.getJSONObject("name").getString("official")
                    var region = ""
                    var subregion = ""
                    var population = ""
                    var continent = ""
                    var borders=""
                    var languages = ""
                    var nativeName = ""
                    var landlocked = ""
                    var area = ""
                    var timezones = ""
                    var independent = ""
                    var unMember = ""
                    var abbreviation = ""
                    var currency = ""

                    if(countryObject.has("region")) {
                        region=countryObject.getString("region")
                    }else{
                        region = "Not Applicable"
                    }

                    if(countryObject.has("subregion")) {
                        subregion=countryObject.getString("subregion")
                    }else{
                        subregion = "Not Applicable"
                    }

                    if(countryObject.has("population")) {
                        population=countryObject.getString("population")
                    }else{
                        population = "Not Applicable"
                    }

                    if(countryObject.has("borders")) {
                         borders=countryObject.getString("borders")
                    }else{
                        borders = "Not Applicable"
                    }

                    if(countryObject.has("languages")) {
                       languages = countryObject.getString("languages")
                    }else{
                        languages = "Not Applicable"
                    }

                    if(countryObject.has("name")) {
                       nativeName = countryObject.getJSONObject("name").getJSONObject("nativeName").toString()
                    }else{
                        nativeName = "Not Applicable"
                    }

                    if(countryObject.has("currencies")) {
                        currency = countryObject.getJSONObject("currencies").toString()
                    }else{
                        currency = "Not Applicable"
                    }


                    if(countryObject.has("landlocked")) {
                        landlocked = countryObject.getString("landlocked")
                    }else{
                        landlocked = "Not Applicable"
                    }

                    if(countryObject.has("area")) {
                        area = countryObject.getString("area")
                    }else{
                        area = "Not Applicable"
                    }

                    if(countryObject.has("timezones")) {
                        timezones = countryObject.getString("timezones")
                    }else{
                        timezones = "Not Applicable"
                    }

                    if(countryObject.has("independent")) {
                        independent = countryObject.getString("independent")
                    }else{
                        independent = "Not Applicable"
                    }

                    if(countryObject.has("unMember")) {
                        unMember = countryObject.getString("unMember")
                    }else{
                        unMember = "Not Applicable"
                    }

                    if(countryObject.has("cca3")) {
                        abbreviation = countryObject.getString("cca3")
                    }else{
                        abbreviation = "Not Applicable"
                    }

                    var capital = ""
                    var description = ""
                    var coatdescription = ""

                    if(countryObject.has("capital")) {
                        capital = countryObject.getJSONArray("capital").getString(0)
                    }else{
                        capital = "Not Applicable"
                    }

                    if(countryObject.has("continents")) {
                        continent = countryObject.getJSONArray("continents").getString(0)
                    }else{
                        continent = "Not Applicable"
                    }



                    val flagUrl = countryObject.getJSONObject("flags").getString("png")

                    if(countryObject.getJSONObject("flags").has("alt"))
                    {
                        description = countryObject.getJSONObject("flags").getString("alt")
                    }


                    var coatUrl = ""

                    if (countryObject.getJSONObject("coatOfArms").length() <= 0){
                        coatUrl ="https://mainfacts.com/media/images/coats_of_arms/cl.png"
                    }else{
                        coatUrl = countryObject.getJSONObject("coatOfArms").getString("png")
                    }


                    if(countryObject.getJSONObject("coatOfArms").has("alt"))
                    {
                        coatdescription = countryObject.getJSONObject("flags").getString("alt")
                    }

                    runOnUiThread {
                        nameView.text = name
                        officialView.text = officialName
                        nativeNameView.text = nativeName
                            .replace("{","")
                            .replace("}","")
                            .replace(",",",  ")
                            .replace("official"," ")
                            .replace("common"," ")
                            .replace("\""," ")
                            .removeRange(0,7)
                            .replaceAfter(","," ")
                            .replace(","," ")

                        currencyView.text = currency
                            .replace("{","")
                            .replace("}","")
                            .replace(",",",  ")
                            .replace("symbol"," ")
                            .replace("name"," ")
                            .replace("\""," ")
                            .removeRange(0,7)
                            .replace(",","- It has the symbol")
                        BarCountryName.text = name
                        regionView.text = region
                        languageView.text = languages
                            .replace("{","")
                            .replace("}","")
                            .replace(",",",  ")
                            .replace("\"","")
                            .replace(":",": ")
                        continentView.text = continent
                        bordersView.text = borders
                            .replace("[","")
                            .replace("]"," ")
                            .replace("\"","")
                            .replace(",",",  ")
                        capitalView.text  = capital
                        areaView.text = "$area km²"
                        timezonesView.text = timezones
                            .replace("[","")
                            .replace("]"," ")
                            .replace("\"","")
                            .replace(",", ",  ")
                        subRegionView.text = subregion
                        landlockedView.text = landlocked
                        populationView.text = population
                        abbreviationView.text = abbreviation
                        unMemberView.text = "$name is ${if (independent == "true") "a member of the United Nations (UN)" else "NOT a member of the United Nations."}"
                        independentView.text = "$name is ${if (independent == "true") "an independent" else "NOT an independent"} country."
                        Picasso.get().load(flagUrl).into(flagView)
                        Picasso.get().load(coatUrl).into(coatView)

                    }
                }
            }
        })
    }
}