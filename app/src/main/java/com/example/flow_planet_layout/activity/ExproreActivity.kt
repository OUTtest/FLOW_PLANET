package com.example.flow_planet_layout.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.SoundMeter

class ExproreActivity : AppCompatActivity() {

    private var soundMeter: SoundMeter? = null
    private var isAudioPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore)

        val Btn_Exprore_Back = findViewById<Button>(R.id.Btn_Exprore_Back)
        val Btn_Exprore_End = findViewById<Button>(R.id.Btn_Exprore_End)
        val Text_Exorore_Timer = findViewById<TextView>(R.id.Text_Exprore_Timer)
        var count = 0
        var minute = ""
        var second = ""

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
}

