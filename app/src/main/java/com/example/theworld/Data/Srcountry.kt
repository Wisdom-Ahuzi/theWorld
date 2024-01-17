package com.example.theworld.Data

data class Srcountry(
    val name:String,
    val capital:String,
    val population:String,
    val region: String,
    val flag: Srflag,
){
//    companion object{
//        val  defaultCountries = arrayListOf<Country>()
//    }
}

data class Srflag(
    val url : String,
    val description: String
)
