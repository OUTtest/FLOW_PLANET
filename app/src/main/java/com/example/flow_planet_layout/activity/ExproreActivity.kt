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
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.SoundMeter
import kotlin.math.pow
import kotlin.math.sqrt

class ExproreActivity : AppCompatActivity() {

    private var soundMeter: SoundMeter? = null
    private var isAudioPermissionGranted = false

    private lateinit var sensorManager: SensorManager
    private lateinit var accelSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore)

        val Btn_Exprore_Back = findViewById<Button>(R.id.Btn_Exprore_Back)
        val Btn_Exprore_End = findViewById<Button>(R.id.Btn_Exprore_End)
        val Text_Exorore_Timer = findViewById<TextView>(R.id.Text_Exprore_Timer)
        var count = 0
        var minute = ""
        var second = ""

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        isAudioPermissionGranted = (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
        if (isAudioPermissionGranted) {
            soundMeter = SoundMeter()
            soundMeter?.start()
        }

        if(intent.hasExtra("count")){
            count = intent.getIntExtra("count", 1)
            count = count * 60000 // 60 * 1000
        }

        Btn_Exprore_Back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        object : CountDownTimer(count.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                minute = ((millisUntilFinished/60000)%60).toString() // 60 * 1000
                second = ((millisUntilFinished/1000)%60).toString()
                Text_Exorore_Timer.text = minute + ":" + second
                val amp = soundMeter?.getAmplitude() ?: 0
                if (amp > 400) {
                    // TODO: do something here
                }
            }

            override fun onFinish() {
                val intent = Intent(this@ExproreActivity, Exprore_EndActivity::class.java).apply {
                    putExtra("bookId", intent.getIntExtra("bookId", -1))
                    putExtra("score", 6)
                }
                soundMeter?.stop()
                startActivity(intent)
                this@ExproreActivity.finish()
            }
        }.start()

        Btn_Exprore_End.setOnClickListener {
            val intent = Intent(this, Exprore_EndActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorListener, accelSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorListener)
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
                // TODO: do something here
            }
        }

        // 사용하지 않음
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        }
    }
}

