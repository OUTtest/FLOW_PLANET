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


class OrbitView : View {

    private val bgSkyPaint = Paint().apply {color = 0 }

    private val sunBitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_sun)
    private val planet0Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_planet1)
    private val planet1Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_planet)
    private val planet2Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap_planet2)
    private val planetBitmapRect = Rect(0, 0, sunBitmap.width-1, sunBitmap.height-1)
    private val planet2BitmapRect = Rect(0, 0, planet2Bitmap.width-1, planet2Bitmap.height-1)

    private val sunRect = RectF()
    private val planetRect = RectF()

    private val orbitPaint = Paint().apply { color = 0xff000000.toInt() } // 궤도 색

    val planets = Array(5) { 0 } // 행성
    private var planetOffset = 0 // 행성 그리는 오프셋

    // 1DP당 픽셀 크기
    private var dotBasePx = 1f

    // ValueAnimator를 통해 주기적 호출을 함
    private val animator = ValueAnimator.ofInt(0,360).apply {
        addUpdateListener {
            val i = it.animatedValue
            planetOffset = i as Int
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
//        planets[0] = 1
//        planets[1] = 0
//        planets[2] = 2
//        planets[3] = 0
//        planets[4] = 3
    }

//    fun setPlanetCount(i: Int) {
//        planets.clear()
//        repeat(i) { j ->
//            planets.add(j*360/i)
//        }
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 1dp당 픽셀 계산
        dotBasePx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics
        )

        val centerX = w/2f
        val centerY = h/2f
        val sunR = 30f * dotBasePx
        sunRect.set(
            centerX - sunR,
            centerY - sunR,
            centerY + sunR,
            centerY + sunR
        )

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

        val contentCenterX = contentWidth / 2f
        val contentCenterY = contentHeight / 2f

        canvas.drawPaint(bgSkyPaint)

        // Draw Planet
        for ((n,i) in planets.withIndex()) {
            if (i == 0) continue
            val j = ((n*360/5)+planetOffset) % 360
            val radius = 15 * dotBasePx

            val r = dotBasePx * (100 + random.nextInt(-40, 40))
            val t = j * PI * 2 / 360
            val x = contentCenterX + (sin(t) * r).toFloat()
            val y = contentCenterY + (cos(t) * r).toFloat()

            planetRect.set(
                x - radius,
                y - radius,
                x + radius,
                y + radius,
            )

            canvas.drawLine(planetRect.centerX(), planetRect.centerY(), contentCenterX, contentCenterY, orbitPaint)

            when (i) {
                1 -> canvas.drawBitmap(planet0Bitmap, planetBitmapRect, planetRect, null)
                2 -> canvas.drawBitmap(planet1Bitmap, planetBitmapRect, planetRect, null)
                else -> {
                    planetRect.right = x + radius * 2.49f
                    planetRect.left = x - radius * 2.49f
                    canvas.drawBitmap(planet2Bitmap, planet2BitmapRect, planetRect, null)
                }
            }
        }

        // Draw sun
        canvas.drawBitmap(sunBitmap, planetBitmapRect, sunRect, null)
    }
}