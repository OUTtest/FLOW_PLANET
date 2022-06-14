package com.example.flow_planet_layout.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
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
                    Intent(this, StatsActivity::class.java)
                R.id.menu_library ->
                    Intent(this, LibraryActivity::class.java)
                R.id.menu_reward ->
                    Intent(this, Reward_Activity::class.java)
                R.id.menu_setting -> null
                R.id.menu_debug ->
                    Intent(this, DebugActivity::class.java)
                else -> null
            }
            if (intent != null) {
                startActivity(intent)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener false
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}