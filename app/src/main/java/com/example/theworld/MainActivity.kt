package com.example.theworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.nav_view,Home()).commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> supportFragmentManager.beginTransaction().add(R.id.nav_view,Home()).commit()
                R.id.regions -> supportFragmentManager.beginTransaction().add(R.id.nav_view,Regions()).commit()
                R.id.search -> supportFragmentManager.beginTransaction().add(R.id.nav_view,Search()).commit()
                R.id.setting -> supportFragmentManager.beginTransaction().add(R.id.nav_view,Settings()).commit()
            }
            true
        }
    }
}