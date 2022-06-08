package com.example.flow_planet_layout.stats

import androidx.lifecycle.*
import com.example.flow_planet_layout.db.dao.FlowLogDao
import com.example.flow_planet_layout.db.entity.FlowLog
import java.lang.IllegalArgumentException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

class StatsViewModel(private val flowLogDao: FlowLogDao): ViewModel() {

    fun getDayLogs(): LiveData<List<FlowLog>> {
        val today = LocalDate.now()
        return flowLogDao.getFromTo(today.atStartOfDay(), today.plusDays(1).atStartOfDay()).asLiveData()
    }

    fun getWeekLogs(): LiveData<List<FlowLog>> {
        val today = LocalDate.now()
        val start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        return flowLogDao.getFromTo(start.atStartOfDay(), start.plusWeeks(1).atStartOfDay()).asLiveData()
    }
}

class StatsViewModelFactory(private val flowLogDao: FlowLogDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            return StatsViewModel(flowLogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}