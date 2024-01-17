package com.example.theworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.theworld.Adapter.SubregionAdapter
import com.example.theworld.Data.Country
import com.example.theworld.Data.Flag
import com.example.theworld.Data.subregion
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


lateinit var subregionGRV: GridView
lateinit var subregionList: List<subregion>


class Regions : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_regions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        subregionGRV = view.findViewById(R.id.idGRV)
        subregionList = ArrayList<subregion>()

        // on below line we are adding data to
        // our course list with image and course name.
        subregionList = subregionList + subregion("Northern Africa", "Africa -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Eastern Africa", "Africa --strict savings automatically. Daily, weekly or monthly. 10% p.a Africa", R.drawable.regions)
        subregionList = subregionList + subregion("Western Africa", " Africa -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Southern Africa", "Africa-- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Middle Africa", " Africa -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)

        subregionList = subregionList + subregion("North America", " North America -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Caribbean", " North America -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Central America", " North America -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)

        subregionList = subregionList + subregion("South America", " South America -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.search)


        subregionList = subregionList + subregion("Northern Europe", " Europe -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Southeast Europe", "Europe --strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.home)
        subregionList = subregionList + subregion("Southern Europe", "Europe -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Central Europe", " Europe -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)


        subregionList = subregionList + subregion("Southern Asia", " Asia -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("South-Eastern Asia", "Asia -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Eastern Asia", " Asia -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Polynesia", " Oceania -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Western Asia", " Asia -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Central Asia", " Asia -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Australia and New Zealand", " Oceania -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)

        subregionList = subregionList + subregion("Melanesia", " Oceania -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)
        subregionList = subregionList + subregion("Micronesia", " Oceania -- strict savings automatically. Daily, weekly or monthly. 10% p.a", R.drawable.setting)


        // on below line we are initializing our course adapter
        // and passing course list and context.
        val sbAdapter = SubregionAdapter(subregionList = subregionList, view.context)

        // on below line we are setting adapter to our grid view.
        subregionGRV.adapter = sbAdapter



    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Regions().apply {
                arguments = Bundle().apply {

                }
            }
    }
}