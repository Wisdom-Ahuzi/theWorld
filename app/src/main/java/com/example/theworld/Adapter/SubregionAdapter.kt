package com.example.theworld.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.theworld.AboutCountry
import com.example.theworld.Data.subregion
import com.example.theworld.R
import com.example.theworld.iindividualSubRegion
import com.example.theworld.sub_region_display

internal class SubregionAdapter (
    private val subregionList: List<subregion>,
    private val context: Context
        ) : BaseAdapter(){
        private var layoutInflater: LayoutInflater? = null
        private lateinit var subregionNameView : TextView
        private lateinit var subregionContinentView : TextView
        private lateinit var subregionImageView : ImageView

        override fun getCount(): Int {
                return subregionList.size
        }

        override fun getItem(p0: Int): Any? {
                return null
        }

        override fun getItemId(p0: Int): Long {
                return 0
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var convertView = p1

            if (layoutInflater == null) {
                    layoutInflater =
                            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            }

            if (convertView == null) {
                    // on below line we are passing the layout file
                    // which we have to inflate for each item of grid view.
                    convertView = layoutInflater!!.inflate(R.layout.sub_region, null)
            }

            subregionNameView = convertView!!.findViewById(R.id.subregionName)
            subregionContinentView = convertView!!.findViewById(R.id.subregionContinent)
            subregionImageView = convertView!!.findViewById(R.id.subregionImage)

            subregionNameView.setText(subregionList.get(p0).subregionName)
            subregionContinentView.setText(subregionList.get(p0).subregionContinent)
            subregionImageView.setImageResource(subregionList.get(p0).subregionImg)

            convertView.setOnClickListener {
                val i = Intent(this.context, iindividualSubRegion::class.java)
                i.putExtra("srname1",subregionList.get(p0).subregionName)
                Toast.makeText(this.context,"You clicked me ---- ${subregionList.get(p0).subregionName} ",Toast.LENGTH_SHORT).show()
                this.context.startActivity(i)
            }

            return convertView
        }

}