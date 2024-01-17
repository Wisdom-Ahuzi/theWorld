package com.example.theworld

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theworld.Adapter.CountriesAdapter
import com.example.theworld.Data.Country
import com.example.theworld.Data.Flag
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class Home : Fragment() {
    private lateinit var countryList : ArrayList<Country>
    private lateinit var countryAdapter : CountriesAdapter


    lateinit var flag : Array<String>
    lateinit var name : Array<String>
    lateinit var capital : Array<String>
    lateinit var population : Array<String>
    lateinit var region : Array<String>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailInitializer()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvContainer)
        recyclerView.setHasFixedSize(true)

        recyclerView?.layoutManager = LinearLayoutManager(context)

        countryAdapter = CountriesAdapter(countryList, parentFragmentManager)

        recyclerView?.adapter = countryAdapter



        countryAdapter.notifyDataSetChanged()

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {

                }
            }
    }


    private fun detailInitializer(){
        countryList = arrayListOf<Country>()

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://restcountries.com/v3.1/all")
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

                        val name = jsonObject.getJSONObject("name").getString("common")
                        var capital = ""
                        var description = ""
                        val population = jsonObject.getString("population")
                        val region = jsonObject.getString("continents").replace("[","").replace("]","").replace("\"","").replace(",",", ")

                        if(jsonObject.has("capital")) {
                            capital = jsonObject.getJSONArray("capital").getString(0)
                        }


                        val flagUrl = jsonObject.getJSONObject("flags").getString("png")

                        if(jsonObject.getJSONObject("flags").has("alt"))
                        {
                            description = jsonObject.getJSONObject("flags").getString("alt")
                        }

                        val newCountry = Country(
                            name = name,
                            capital = capital,
                            population = population,
                            region = region,
                            flag = Flag(
                                url= flagUrl,
                                description = description
                            )

                        )

                        countryList.add(newCountry)


                        Handler(Looper.getMainLooper()).post(Runnable { //do stuff like remove view etc
                            countryAdapter.notifyDataSetChanged()
                        })

                    }
                }
            }
    })

}}