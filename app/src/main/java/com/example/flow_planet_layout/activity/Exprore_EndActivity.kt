package com.example.flow_planet_layout.activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope

import com.example.flow_planet_layout.R
import com.example.flow_planet_layout.db.DBApplication
import com.example.flow_planet_layout.db.entity.Page
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class Exprore_EndActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var rateBar: ImageView
    private lateinit var bookEntry: ConstraintLayout

    private lateinit var pageTitle: EditText
    private lateinit var pageBody: EditText

    private val rocketValueAnimator: ValueAnimator = ValueAnimator.ofFloat(-1200f, 0f).apply {
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
                rateBar.translationY = -400f
                rateBar.animate().translationY(0f).setDuration(1000).withEndAction {
                    bookEntry.visibility = View.VISIBLE
                    bookEntry.alpha = 0f
                    bookEntry.animate().alpha(1f).setDuration(1000).start()
                }
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exprore_end)

        val bookId = intent.getIntExtra("bookId", -1)
        val score = intent.getIntExtra("score", -1)
        if ((bookId == -1) or (score == -1)) {
            Toast.makeText(this, "Invalid parameter", Toast.LENGTH_SHORT).show()
            finish()
        }


        imageView = findViewById(R.id.imageView4)
        imageView2 = findViewById(R.id.imageView5)

        rateBar = findViewById(R.id.iv_ratebar)
        rateBar.translationY = -400f

        bookEntry = findViewById(R.id.book_entry)
        bookEntry.visibility = View.INVISIBLE

        pageTitle = bookEntry.findViewById(R.id.editTextTextPersonName)
        pageBody= bookEntry.findViewById(R.id.editTextTextMultiLine)

        val pageDao = (application as DBApplication).pageDao

        bookEntry.findViewById<Button>(R.id.btn_entry_save).setOnClickListener {
            lifecycleScope.launch {
                pageDao.put(Page(
                    time = LocalDateTime.now(),
                    bookId = bookId,
                    title = pageTitle.text.toString(),
                    data = pageBody.text.toString()
                ))
            }
            finish()
        }

        bookEntry.findViewById<Button>(R.id.btn_entry_cancel).setOnClickListener {
            finish()
        }

        rocketValueAnimator.start()
        setRateBarStar(score)
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