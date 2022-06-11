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
import java.time.temporal.TemporalAdjusters

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
            val chart = findViewById<BarChart>(R.id.chart_month)
            chart.data = BarData(BarDataSet( arr, ""))
            statsViewModel.getMonthLogs().observe(viewLifecycleOwner) {
                arr.forEach { i -> i.y = 0f }
                it.forEach { flowLog ->
                    arr[(flowLog.start.dayOfMonth - 1) % 7].y += flowLog.duration
                }
                chart.invalidate()
            }

        }
    }

}