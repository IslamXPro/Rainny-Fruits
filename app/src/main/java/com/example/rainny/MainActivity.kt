package com.example.rainny

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rainny.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var pos = 1
    private var touchPos = 1
    private var highScore = 0
    var score: Int = 1
    private var levelScore = 10
    private var view = View.VISIBLE
    private var gone = View.INVISIBLE
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        handler = Handler()
        viewTomchi(true)
        binding.paqir.visibility = gone
        binding.btnStart.setOnClickListener {
            binding.btnStart.playAnimation()
            binding.cardstart.visibility = View.GONE
            startGame()
            binding.paqir.visibility = view
        }

/*        binding.paqirRight.setOnClickListener {
            binding.tomchiRight.translationX = 120F
        }*/

/*        while (score <= levelScore) {
            continueGame()
            score++
            Log.d(TAG, "onCreate: $score")
            if (score == levelScore)
                break
        }*/
        binding.paqirLeft.setOnClickListener {
            touchPos = 1
            binding.paqir.translationX = -240f
        }
        binding.paqirCenter.setOnClickListener {
            touchPos = 2
            binding.paqir.translationX = 0f
        }
        binding.paqirRight.setOnClickListener {
            touchPos = 3
            binding.paqir.translationX = 240f
        }
    }

    private fun startGame() {
        binding.paqirCenter.setOnClickListener {
            touchPos = 2
            binding.paqir.translationX = 0f
            continueGame()
        }
    }

    private fun continueGame() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        binding.tomchiLeft.visibility = view
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos()
                        levelWin()
                        binding.tomchiLeft.visibility = gone

                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
            2 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiCenter.startAnimation(anim)

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        binding.tomchiCenter.visibility = view
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos()
                        levelWin()
                        binding.tomchiCenter.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
            3 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiRight.startAnimation(anim)

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        binding.tomchiRight.visibility = view
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun checkPos() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = 10
            Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
            continueGame()
        }
    }

    private fun levelWin() {
        if (score == levelScore) {
            Toast.makeText(this, "level 2", Toast.LENGTH_SHORT).show()
        } else {
            score++
            binding.point.text = score.toString()
            continueGame()
        }
    }

    private fun getRandomPos(): Int {
        val rand = Random.nextInt(1, 4)
        pos = rand
        return rand
    }

    private fun viewTomchi(check: Boolean) {
        if (check) {
            binding.tomchiLeft.visibility = gone
            binding.tomchiCenter.visibility = gone
            binding.tomchiRight.visibility = gone
        }
    }

    private fun viewPaqir(touch: Boolean) {
        if (touch) {
            binding.paqirCenter.visibility = gone
        }
    }
}