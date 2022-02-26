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
    private var fruitpos = 1
    private var touchPos = 1
    private var highScore = 0
    var score: Int = 1
    private var levelScore1 = 10
    private var levelScore2 = 25
    private var levelScore3 = 40
    private var levelScore4 = 60
    private var levelScore5 = 100
    private var view = View.VISIBLE
    private var gone = View.INVISIBLE
    lateinit var handler: Handler
    var images: IntArray = intArrayOf(
        R.drawable.balgariskiy,
        R.drawable.banan,
        R.drawable.baqlajon,
        R.drawable.chesnok,
        R.drawable.kartoshka,
        R.drawable.olma,
        R.drawable.olov,
        R.drawable.pomidor,
        R.drawable.qalampir,
        R.drawable.qulupnay,
        R.drawable.sabzi,
        R.drawable.shaftoli
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        handler = Handler()
        viewTomchi(true)

        binding.btnStart.setOnClickListener {
            binding.cardstart.visibility = View.GONE
            startGame()
            binding.paqirCenter.visibility = view
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
        binding.paqirtouchLeft.setOnClickListener {
            touchPos = 1
            viewPaqir(true)
            binding.paqirLeft.visibility = view
        }
        binding.paqirtouchCenter.setOnClickListener {
            touchPos = 2
            viewPaqir(true)
            binding.paqirCenter.visibility = view
        }
        binding.paqirtouchRight.setOnClickListener {
            touchPos = 3
            viewPaqir(true)
            binding.paqirRight.visibility = view
        }
    }

    private fun startGame() {
        touchPos = 2
        viewPaqir(true)
        binding.paqirCenter.visibility = view
        continueGame1Level()
    }


    private fun checkPos1() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = levelScore1
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos2() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = levelScore2
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos3() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = levelScore3
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos4() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = levelScore4
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos5() {

        if (pos == touchPos) {
            binding.progress.progress = score
            binding.progress.max = levelScore5
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun levelWin() {
        if (score == levelScore1) {
            Toast.makeText(this, "level 2", Toast.LENGTH_SHORT).show()
            continueGame2Level()
        } else if (score == levelScore2) {
            Toast.makeText(this, "Level 3", Toast.LENGTH_SHORT).show()
            continueGame3Level()
        } else if (score == levelScore3) {
            Toast.makeText(this, "Level 4", Toast.LENGTH_SHORT).show()
            continueGame4Level()
        } else if (score == levelScore4) {
            Toast.makeText(this, "Level 5", Toast.LENGTH_SHORT).show()
            continueGame5Level()
        } else if (score == levelScore5) {
            Toast.makeText(this, "You are winner! :)", Toast.LENGTH_SHORT).show()
        } else if (score < 10) {
            score++
            binding.point.text = score.toString()
            continueGame2Level()
        } else if (score < 25) {
            score++
            binding.point.text = score.toString()
            continueGame3Level()
        } else if (score < 40) {
            score++
            binding.point.text = score.toString()
            continueGame4Level()
        } else if (score < 60) {
            score++
            binding.point.text = score.toString()
            continueGame5Level()
        } else if (score < 100) {
            score++
            binding.point.text = score.toString()
            continueGame5Level()
        }
    }

    private fun continueGame1Level() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)

                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomLeft()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos1()
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
                        getFruitRandomCenter()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos1()
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
                        getFruitRandomRight()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos1()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun continueGame2Level() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)
                anim.duration = 1500
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomLeft()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos2()
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
                anim.duration = 1500
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomCenter()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos2()
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
                anim.duration = 1500
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomRight()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos2()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun continueGame3Level() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)
                anim.duration = 1300
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomLeft()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos3()
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
                anim.duration = 1300
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomCenter()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos3()
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
                anim.duration = 1300
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomRight()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos3()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun continueGame4Level() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)
                anim.duration = 1100
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomLeft()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos4()
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
                anim.duration = 1100
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomCenter()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos4()
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
                anim.duration = 1100
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomRight()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos4()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun continueGame5Level() {
        when (getRandomPos()) {
            1 -> {

                val anim = AnimationUtils.loadAnimation(this, R.anim.road1_anim)
                binding.tomchiLeft.startAnimation(anim)
                anim.duration = 900
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomLeft()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos5()
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
                anim.duration = 900
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomCenter()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos5()
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
                anim.duration = 900
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        getFruitRandomRight()
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        checkPos5()
                        levelWin()
                        binding.tomchiRight.visibility = gone
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }
        }
    }

    private fun getRandomPos(): Int {
        val rand = Random.nextInt(1, 4)
        pos = rand
        return rand
    }

    private fun getFruitRandomLeft(): Int {
        val random = Random.nextInt(images.size)
        fruitpos = random
        binding.tomchiLeft.setImageResource(images[random])
        return random
    }

    private fun getFruitRandomCenter(): Int {
        val random = Random.nextInt(images.size)
        fruitpos = random
        binding.tomchiCenter.setImageResource(images[random])
        return random
    }

    private fun getFruitRandomRight(): Int {
        val random = Random.nextInt(images.size)
        fruitpos = random
        binding.tomchiRight.setImageResource(images[random])
        return random
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
            binding.paqirLeft.visibility = gone
            binding.paqirRight.visibility = gone
        }
    }
}