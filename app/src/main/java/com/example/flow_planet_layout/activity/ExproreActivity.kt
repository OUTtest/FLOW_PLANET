package com.example.flow_planet_layout.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.SoundMeter
import com.example.flow_planet_layout.db.DBApplication
import com.example.flow_planet_layout.db.dao.FlowLogDao
import com.example.flow_planet_layout.db.entity.FlowLog
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.pow
import kotlin.math.sqrt

class ExproreActivity : AppCompatActivity() {

    private var soundMeter: SoundMeter? = null
    private var isAudioPermissionGranted = false

    private lateinit var sensorManager: SensorManager
    private lateinit var accelSensor: Sensor

    private lateinit var flowLogDao: FlowLogDao
    private val startedTime = LocalDateTime.now()
    private var targetTime = 0L
    private var leftTime = 0L

    private lateinit var Text_Exorore_Timer: TextView
    private var timer: CountDownTimer? = null

    private lateinit var Btn_Exprore_Reset: Button
    private var isAnomaly = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore)

        val Btn_Exprore_Back = findViewById<Button>(R.id.Btn_Exprore_Back)
        val Btn_Exprore_End = findViewById<Button>(R.id.Btn_Exprore_End)
        Btn_Exprore_Reset = findViewById(R.id.Btn_Exprore_Reset)
        Text_Exorore_Timer = findViewById(R.id.Text_Exprore_Timer)

        flowLogDao = (application as DBApplication).flowLogDao
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        isAudioPermissionGranted = (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        if (isAudioPermissionGranted) {
            soundMeter = SoundMeter()
            soundMeter?.start()
        }

        if(intent.hasExtra("count")){
            targetTime = intent.getIntExtra("count", 1) * 60000L // 60 * 1000
        }

        Btn_Exprore_Back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        startTimer(targetTime)

        Btn_Exprore_End.setOnClickListener {
            finishExplore()
        }

        Btn_Exprore_Reset.setOnClickListener {
            if (isAnomaly){
                Btn_Exprore_Reset.visibility = View.INVISIBLE
                isAnomaly = false
                startTimer(leftTime)
            }
        }
    }

    private fun finishExplore() {
        val consumedTime = ((targetTime - leftTime)/60000).toInt()
        if (consumedTime > 0) {
            lifecycleScope.launch {
                flowLogDao.put(
                    FlowLog(
                        startedTime,
                        consumedTime
                        )
                )
            }
        }
        val intent = Intent(this, Exprore_EndActivity::class.java).apply {
            putExtra("bookId", intent.getIntExtra("bookId", -1))
            putExtra("score", 6)
        }
        startActivity(intent)
        this@ExproreActivity.finish()
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorListener, accelSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        soundMeter?.stop()
        timer?.cancel()
    }

    private val sensorListener = object: SensorEventListener {
        override fun onSensorChanged(ev: SensorEvent?) {
            if (ev == null) return
            val accel = sqrt(
                ev.values[0].pow(2)
                        + ev.values[1].pow(2)
                        + ev.values[2].pow(2)
            ) - SensorManager.GRAVITY_EARTH // 가속도의 제곱평균 - 중력

            if (accel > 3) {
                if (!isAnomaly){
                    Btn_Exprore_Reset.visibility = View.VISIBLE
                    isAnomaly = true
                    timer?.cancel()
                }
            }
        }

        // 사용하지 않음
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }

    private fun startTimer(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis,1000){
            override fun onTick(millisUntilFinished: Long) {
                onTimerTick(millisUntilFinished)
                leftTime -= 1000
            }

            override fun onFinish() {
                leftTime = 0
                onTimerFinished()
            }
        }.start()
        leftTime = millis
    }

    private fun onTimerTick(millisUntilFinished: Long) {
        val minute = ((millisUntilFinished/60000)%60).toString() // 60 * 1000
        val second = ((millisUntilFinished/1000)%60).toString()
        Text_Exorore_Timer.text = minute + ":" + second
        val amp = soundMeter?.getAmplitude() ?: 0
        if (amp > 400) {
            if (!isAnomaly){
                Btn_Exprore_Reset.visibility = View.VISIBLE
                isAnomaly = true
                timer?.cancel()
            }
        }
    }

    private fun onTimerFinished() {
        finishExplore()
    }
}

