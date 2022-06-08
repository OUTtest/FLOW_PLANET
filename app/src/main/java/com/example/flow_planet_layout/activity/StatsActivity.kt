package com.example.flow_planet_layout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.flow_planet_layout.stats.DayStatsFragment
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.stats.WeekStatsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        viewPager2.adapter = StatsPagerAdapter(this)

        // ViewPager -> NavBar 연동
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu[position].isChecked = true
            }
        })

        // NavBar -> ViewPager 연동
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_day -> {
                    viewPager2.currentItem = 0
                    true
                }
                R.id.menu_week -> {
                    viewPager2.currentItem = 1
                    true
                }
                R.id.menu_month -> {
                    viewPager2.currentItem = 2
                    true
                }
                R.id.menu_year -> {
                    viewPager2.currentItem = 3
                    true
                }
                else -> {
                    false
                }
            }
        }

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener{
            this.finish()
        }
    }
    
}

class StatsPagerAdapter(private val fa:FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DayStatsFragment()
            1 -> WeekStatsFragment()
            2 -> DayStatsFragment()
            3 -> DayStatsFragment()
            else -> throw IndexOutOfBoundsException()
        }
    }
}