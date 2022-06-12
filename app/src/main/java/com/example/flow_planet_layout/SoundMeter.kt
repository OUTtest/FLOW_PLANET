package com.example.flow_planet_layout

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import kotlin.math.abs

class SoundMeter {
    // 버퍼의 가능한 최소 크기
    private val bufferMinSize = AudioRecord.getMinBufferSize(
        8000,
        AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT
    )
    private val audioBuffer = ShortArray(bufferMinSize)

    @SuppressLint("MissingPermission")
    private var recorder = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        8000,
        AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
        bufferMinSize
    )

    fun getAmplitude(): Int {
        recorder.read(audioBuffer,0, bufferMinSize) // 버퍼에 읽어들인 후
        return audioBuffer.maxOf { abs(it.toInt()) } // 개별 값의 절대값 중 최대를 구한다
    }

    fun start() {
        recorder.startRecording()
    }

    fun stop() {
        recorder.stop()
    }
}