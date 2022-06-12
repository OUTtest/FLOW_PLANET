package com.example.flow_planet_layout.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.flow_planet_layout.ui.OrbitView
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class MonthStatsFragment : Fragment() {

    private val arr = ArrayList<BarEntry>().apply {
        for (i in 0..4){
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
        return inflater.inflate(R.layout.fragment_month_stats, container, false).apply {
            val orbitView = findViewById<OrbitView>(R.id.iv_orbit)
            val chart = findViewById<BarChart>(R.id.chart_month).apply {
                axisRight.isEnabled = false
                axisLeft.axisMinimum = 0f
            }
            chart.data = BarData(BarDataSet( arr, ""))
            statsViewModel.getMonthLogs().observe(viewLifecycleOwner) {
                arr.forEach { i -> i.y = 0f }
                it.forEach { flowLog ->
                    arr[(flowLog.start.dayOfMonth - 1) % 7].y += flowLog.duration
                }
                chart.invalidate()

                arr.withIndex().forEach { (n, it) ->
                    orbitView.planets[n] =  (it.y / 100).toInt() // 1/100min
                }
            }

        }
    }

}