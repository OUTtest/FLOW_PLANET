package com.example.flow_planet_layout.ui.stats

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*

class DayStatsFragment : Fragment() {

    private val arr = ArrayList<BarEntry>().apply {
        for (i in 0..23){
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
        return inflater.inflate(R.layout.fragment_day_stats, container, false).apply {
            val chart = findViewById<BarChart>(R.id.chart_day).apply {
                axisRight.isEnabled = false
                axisLeft.axisMinimum = 0f
            }
            val imageView = findViewById<ImageView>(R.id.iv_base)

            chart.data = BarData(BarDataSet( arr, ""))
            statsViewModel.getDayLogs().observe(viewLifecycleOwner) {
                var total = 0
                arr.forEach { i -> i.y = 0f }
                it.forEach { flowLog ->
                    arr[flowLog.start.hour].y += flowLog.duration
                    total += flowLog.duration
                }

                for (i in 1..4) {
                    val d = (imageView.drawable as LayerDrawable).getDrawable(i)
                    if (total > i * 30) {
                        d.alpha = 255
                    } else {
                        d.alpha = 0
                    }
                }

                chart.invalidate()
            }
        }
    }

}