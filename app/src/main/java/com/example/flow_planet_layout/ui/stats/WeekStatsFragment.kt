package com.example.flow_planet_layout.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.flow_planet_layout.ui.PlanetView
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*

class WeekStatsFragment : Fragment() {

    private val arr = ArrayList<BarEntry>().apply {
        for (i in 0..7){
            add(BarEntry(i.toFloat(), 0f))
        }
    }

    private val statsViewModel: StatsViewModel by activityViewModels {
        StatsViewModelFactory((activity?.application as DBApplication).flowLogDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week_stats, container, false).apply {
            val chart = findViewById<BarChart>(R.id.chart_week).apply {
                axisRight.isEnabled = false
                axisLeft.axisMinimum = 0f
            }
            val planetView = findViewById<PlanetView>(R.id.iv_planet)
            chart.data = BarData(BarDataSet( arr, ""))
            statsViewModel.getWeekLogs().observe(viewLifecycleOwner) {
                var total = 0
                arr.forEach { i -> i.y = 0f }
                it.forEach { flowLog ->
                     arr[flowLog.start.dayOfWeek.value -1].y += flowLog.duration
                    total += flowLog.duration
                }
                planetView.setSatelliteCount(total / 120) // 1/120min
                chart.invalidate()

            }

        }
    }

}