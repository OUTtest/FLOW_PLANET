package com.example.flow_planet_layout.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.flow_planet_layout.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Btn_MAIN_Menu = findViewById<Button>(R.id.Btn_MAIN_Menu)
        val Btn_MAIN_Start = findViewById<Button>(R.id.Btn_MAIN_Start)
        drawerLayout = findViewById(R.id.drawer_main)
        navView = findViewById(R.id.navview_main)

        Btn_MAIN_Menu.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }

        Btn_MAIN_Start.setOnClickListener{
            val intent = Intent(this, Space_Station_Activity::class.java)
            startActivity(intent)
        }

        navView.setNavigationItemSelectedListener {
            val intent: Intent? = when (it.itemId) {
                R.id.menu_spacestation ->
                    Intent(this, Statistics_Day_Activity::class.java)
                R.id.menu_library ->
                    Intent(this, LibraryActivity::class.java)
                R.id.menu_reward ->
                    Intent(this, Reward_Activity::class.java)
                R.id.menu_setting -> null
                else -> null
            }
            if (intent != null) {
                startActivity(intent)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener false
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}