package com.example.theworld.Data

data class Country(
    val name:String,
    val capital:String,
    val population:String,
    val region: String,
    val flag: Flag,
    ){
//    companion object{
//        val  defaultCountries = arrayListOf<Country>()
//    }
}

data class Flag(
    val url : String,
    val description: String
)