package com.example.flow_planet_layout.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*

class YearStatsFragment : Fragment() {

    private val statsViewModel: StatsViewModel by activityViewModels {
        StatsViewModelFactory((activity?.application as DBApplication).flowLogDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_year_stats, container, false).apply {
        }
    }

}