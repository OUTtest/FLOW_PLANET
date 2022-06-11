package com.example.flow_planet_layout.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView

import com.example.flow_planet_layout.R

class Exprore_EndActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var imageView2: ImageView
    lateinit var rateBar: ImageView

    val rocketValueAnimator = ValueAnimator.ofFloat(-1200f, 0f).apply {
        addUpdateListener {
            imageView.translationY = it.animatedValue as Float
            imageView2.translationY = it.animatedValue as Float
        }
        duration = 2000
        interpolator = DecelerateInterpolator()
        addListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                rateBarAnimator.start()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }

    val rateBarAnimator = ValueAnimator.ofFloat(-400f, 0f).apply {
        addUpdateListener {
            rateBar.translationY = it.animatedValue as Float
        }
        duration = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore_end)

        imageView = findViewById(R.id.imageView4)
        imageView2 = findViewById(R.id.imageView5)

        rateBar = findViewById(R.id.iv_ratebar)
        rateBar.translationY = -400f

        rocketValueAnimator.start()
        setRateBarStar(3)
    }

    fun setRateBarStar(n: Int) {
        val layer = rateBar.drawable as LayerDrawable
        val v = (resources.displayMetrics.density * 70).toInt() // 70dp
        repeat (6) {
            if (n > it) {
                layer.setLayerSize(it+1, v, v)
            } else {
                layer.setLayerSize(it+1, 0, 0)
            }
        }
    }
}