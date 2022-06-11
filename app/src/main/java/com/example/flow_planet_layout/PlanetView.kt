package com.example.flow_planet_layout

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


class PlanetView : View {

    private val bgSkyPaint = Paint().apply {color = 0 }

    private val planetBitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_planet)
    private val planetBitmapRect = Rect(0, 0, planetBitmap.width-1, planetBitmap.height-1)


    private val planetRect = RectF()
    private val planetPaint = Paint().apply { color = 0xfffeffb3.toInt() } // 행성 색
    private val satellitePaint = Paint().apply { color= 0xffbdbdbd.toInt() } // 위성 색


    private val satelliteRect = RectF()
    private val satelliteBitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_satellite)
    private val satelliteBitmapRect = Rect(0, 0, satelliteBitmap.width-1, satelliteBitmap.height-1)

    private val orbitPaint = Paint().apply { color = 0xff000000.toInt() } // 궤도 색
    private val orbitPoints = FloatArray(360*2) // 궤도 좌표 (x1, y1, x2, y2, ...)
    private val orbitMatrix = Matrix() // 궤도 왜곡을 위한 행렬

    private val satellites = ArrayList<Int>() // 위성 위치 (0~360)
    private var satelliteOffset = 0 // 위성 그리는 오프셋

    // 1DP당 픽셀 크기
    private var dotBasePx = 1f

    // ValueAnimator를 통해 주기적 호출을 함
    private val animator = ValueAnimator.ofInt(0,360).apply {
        addUpdateListener {
            val i = it.animatedValue
            satelliteOffset = i as Int
            invalidate()
        }
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        duration = 10000
        start()
    }

    // 같은 상태를 유지하기 위한 랜덤 시드
    var randomSeed = 0

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        //setSatelliteCount(5)
    }

    // 위성 수 설정
    fun setSatelliteCount(i: Int) {
        satellites.clear()
        val t = (0 until 36).shuffled() // 중복을 막기 위해 0~35 무작위 리스트를 생성
        repeat(i) { j ->
            satellites.add(t[j]*10)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 1dp당 픽셀 계산
        dotBasePx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics
        )

        val centerX = w/2f
        val centerY = h/2f
        val planetR = 30f * dotBasePx
        planetRect.set(
            centerX - planetR,
            centerY - planetR,
            centerY + planetR,
            centerY + planetR
        )

        // 궤도 좌표 계산
        for (i in 0 until 360) {
            val r = dotBasePx * 100
            val t = i * PI * 2 / 360
            val x = centerX + (sin(t) * r)
            val y = centerY + (cos(t) * r)
            orbitPoints[i*2] = x.toFloat()
            orbitPoints[i*2+1] = y.toFloat()
        }

        // 궤도 좌표 왜곡
        orbitMatrix.setSkew(0.5f, 0.1f, w/2f, h/2f)
        orbitMatrix.mapPoints(orbitPoints)

        // 궤도 점 크기 설정
        orbitPaint.strokeWidth = dotBasePx * 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val random = Random(randomSeed)

        // 지금 당장 패딩은 사용하지 않지만, 일단 남겨놓음
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom

        canvas.drawPaint(bgSkyPaint)

        // Draw planet
        canvas.drawBitmap(planetBitmap, planetBitmapRect, planetRect, null)

        // Satellite Path
        canvas.drawPoints(orbitPoints, orbitPaint)

        // Draw Satellite
        satellites.forEach { i ->
            val j = (i+satelliteOffset) % 360
            val r = 15 * dotBasePx
            satelliteRect.set(
                orbitPoints[j*2] - r,
                orbitPoints[j*2+1] - r,
                orbitPoints[j*2] + r,
                orbitPoints[j*2+1] + r,

            )
            canvas.drawBitmap(satelliteBitmap, satelliteBitmapRect, satelliteRect, null)
            //canvas.drawCircle(orbitPoints[j*2], orbitPoints[j*2+1], r, satellitePaint)
        }

    }
}