package com.islamxpro.rainny

import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.*
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.islamxpro.rainny.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var pos = 0
    private var fruitpos = 1
    private var touchPos = 0
    private var score: Int = 0
    private var failScore = 0
    private var levelScore1 = 10
    private var levelScore2 = 25
    private var levelScore3 = 40
    private var levelScore4 = 60
    private var levelScore5 = 100
    private var view = View.VISIBLE
    private var gone = View.INVISIBLE
    private lateinit var handler: Handler
    private lateinit var vibrator: Vibrator
    private lateinit var getPointMusic: MediaPlayer
    private lateinit var failPointmusic: MediaPlayer
    private lateinit var gameOverMus: MediaPlayer
    private lateinit var gameWin: MediaPlayer
    private lateinit var gameLevelWin: MediaPlayer
    private lateinit var gameOverMusic: MediaPlayer
    private var lost = false
    private var life = false
    private var win = false
    private var images: IntArray = intArrayOf(
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
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        window?.statusBarColor = Color.TRANSPARENT
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        handler = Handler()
        viewTomchi(true)

        binding.btnStart.setOnClickListener {
            binding.cardstart.visibility = View.GONE
            binding.countAnim.visibility = view
            binding.countAnim.playAnimation()
            binding.countAnim.speed = 1.2f
            handler.postDelayed({
                startGame()
                binding.paqirCenter.visibility = view
                binding.countAnim.visibility = View.GONE
            }, 3700)
        }

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
        nullPoint()
        viewPaqir(true)
        binding.paqirCenter.visibility = view
        lost = true
        life = true
        lifeTime()
        continueGame1Level()
    }

    private fun againGame() {
        lost = true
        viewPaqir(true)
        lifeTime()
        nullPoint()
        binding.paqirCenter.visibility = view
        continueGame1Level()
    }

    private fun nullPoint() {
        failScore = 0
        score = 0
        touchPos = 2
        binding.point.text = score.toString()
        binding.progress.progress = score
    }

    private fun lifeTime() {
        binding.life1.frame = 0
        binding.life2.frame = 0
        binding.life3.frame = 0
        binding.life1.pauseAnimation()
        binding.life2.pauseAnimation()
        binding.life3.pauseAnimation()
        binding.life1.visibility = View.VISIBLE
        binding.life2.visibility = View.VISIBLE
        binding.life3.visibility = View.VISIBLE
    }

    private fun checkPos1() {

        if (pos == touchPos) {
            binding.progress.progress = score
            getMusic()
            binding.progress.max = levelScore1 - 1
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            failScore += 1
            vibrate()
            crashAnim()
            failMusic()
            failScoreFun()
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos2() {

        if (pos == touchPos) {
            binding.progress.progress = score
            getMusic()
            binding.progress.max = levelScore2 - 1
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            failScore += 1
            vibrate()
            crashAnim()
            failMusic()
            failScoreFun()
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos3() {

        if (pos == touchPos) {
            binding.progress.progress = score
            getMusic()
            binding.progress.max = levelScore3 - 1
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            failScore += 1
            vibrate()
            crashAnim()
            failMusic()
            failScoreFun()
            //Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos4() {

        if (pos == touchPos) {
            binding.progress.progress = score
            getMusic()
            binding.progress.max = levelScore4 - 1
            //Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            failScore += 1
            vibrate()
            crashAnim()
            failMusic()
            failScoreFun()
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPos5() {

        if (pos == touchPos) {
            binding.progress.progress = score
            getMusic()
            binding.progress.max = levelScore5 - 1
            //   Toast.makeText(this, "Score +1", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            failScore += 1
            vibrate()
            crashAnim()
            failMusic()
            failScoreFun()
            // Toast.makeText(this, "Lost -1", Toast.LENGTH_SHORT).show()
        }
    }

    private fun levelWin1() {
        if (score == levelScore1 - 1) {
            levelWinMusic()
            pauseGame()
            binding.point.text = levelScore1.toString()
            binding.levelWinAnim.visibility = view
            binding.levelWinAnim.playAnimation()
            Snackbar.make(binding.root, "Level 2", Snackbar.LENGTH_SHORT).show()
            handler.postDelayed({
                binding.levelWinAnim.visibility = View.GONE
                binding.btnContinue.visibility = view
                binding.btnContinue.setOnClickListener {
                    continueGame2Level()
                    resumeGame()
                    binding.btnContinue.visibility = View.GONE
                }
            }, 1500)
        } else {
            score++
            binding.point.text = score.toString()
            continueGame1Level()
        }
    }

    private fun levelWin2() {
        if (score == levelScore2 - 1) {
            levelWinMusic()
            pauseGame()
            binding.point.text = levelScore2.toString()
            binding.levelWinAnim.visibility = view
            binding.levelWinAnim.playAnimation()
            Snackbar.make(binding.root, "Level 3", Snackbar.LENGTH_SHORT).show()
            handler.postDelayed({
                binding.levelWinAnim.visibility = View.GONE
                binding.btnContinue.visibility = view
                binding.btnContinue.setOnClickListener {
                    continueGame3Level()
                    resumeGame()
                    binding.btnContinue.visibility = View.GONE
                }
            }, 1500)
        } else {
            score++
            binding.point.text = score.toString()
            continueGame2Level()
        }
    }

    private fun levelWin3() {
        if (score == levelScore3 - 1) {
            levelWinMusic()
            pauseGame()
            binding.point.text = levelScore3.toString()
            binding.levelWinAnim.visibility = view
            binding.levelWinAnim.playAnimation()
            Snackbar.make(binding.root, "Level 4", Snackbar.LENGTH_SHORT).show()
            handler.postDelayed({
                binding.levelWinAnim.visibility = View.GONE
                binding.btnContinue.visibility = view
                binding.btnContinue.setOnClickListener {
                    continueGame4Level()
                    resumeGame()
                    binding.btnContinue.visibility = View.GONE
                }
            }, 1500)
        } else {
            score++
            binding.point.text = score.toString()
            continueGame3Level()
        }
    }

    private fun levelWin4() {
        if (score == levelScore4 - 1) {
            levelWinMusic()
            pauseGame()
            binding.point.text = levelScore4.toString()
            binding.levelWinAnim.visibility = view
            binding.levelWinAnim.playAnimation()
            Snackbar.make(binding.root, "Level 4", Snackbar.LENGTH_SHORT).show()
            handler.postDelayed({
                binding.levelWinAnim.visibility = View.GONE
                binding.btnContinue.visibility = view
                binding.btnContinue.setOnClickListener {
                    continueGame5Level()
                    resumeGame()
                    binding.btnContinue.visibility = View.GONE
                }
            }, 1500)
        } else {
            score++
            binding.point.text = score.toString()
            continueGame4Level()
        }
    }

    private fun levelWin5() {
        if (score == levelScore5 - 1) {
            win = true
            binding.point.text = levelScore5.toString()
            Snackbar.make(binding.root, "You are winner!", Snackbar.LENGTH_SHORT).show()
            pauseGame()
            gameWin()
            handler.postDelayed({
                if (win) {
                    binding.btnReplay.visibility = view
                    binding.btnReplay.setOnClickListener {
                        binding.winnerPartAnim.visibility = View.GONE
                        binding.winnerAnim.visibility = View.GONE
                        startGame()
                    }
                }
            },3000)
        } else {
            score++
            binding.point.text = score.toString()
            continueGame5Level()
        }
    }

    private fun gameWin() {
        gameWin = MediaPlayer.create(this,R.raw.game_win)
        gameWin.start()
        binding.winnerAnim.visibility = view
        binding.winnerPartAnim.visibility = view
        binding.winnerPartAnim.playAnimation()
        binding.winnerAnim.playAnimation()
        binding.winnerPartAnim.loop(true)
    }

    private fun continueGame1Level() {
        if (lost) {
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
                            levelWin1()
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
                            levelWin1()
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
                            levelWin1()
                            binding.tomchiRight.visibility = gone
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }
                    })
                }
            }
        }
    }

    private fun continueGame2Level() {
        if (lost) {
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
                            levelWin2()
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
                            levelWin2()
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
                            levelWin2()
                            binding.tomchiRight.visibility = gone
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }
                    })
                }
            }
        }
    }

    private fun continueGame3Level() {
        if (lost) {
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
                            levelWin3()
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
                            levelWin3()
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
                            levelWin3()
                            binding.tomchiRight.visibility = gone
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }
                    })
                }
            }
        }
    }

    private fun continueGame4Level() {
        if (lost) {
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
                            levelWin4()
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
                            levelWin4()
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
                            levelWin4()
                            binding.tomchiRight.visibility = gone
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }
                    })
                }
            }
        }
    }

    private fun continueGame5Level() {
        if (lost) {
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
                            levelWin5()
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
                            levelWin5()
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
                            levelWin5()
                            binding.tomchiRight.visibility = gone
                        }

                        override fun onAnimationRepeat(p0: Animation?) {

                        }
                    })
                }
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

    private fun vibrate() {
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            vibrator.vibrate(500)
        }
    }

    private fun crashAnim() {
        when (pos) {
            1 -> {
                goneAnim()
                binding.crashLeft.visibility = view
                binding.crashLeft.playAnimation()
                //    handler.postDelayed({},500)
            }
            2 -> {
                goneAnim()
                binding.crashCenter.visibility = view
                binding.crashCenter.playAnimation()
                //   handler.postDelayed({},500)
            }
            3 -> {
                goneAnim()
                binding.crashRight.visibility = view
                binding.crashRight.playAnimation()
                //    handler.postDelayed({},500)
            }
            else -> {

            }
        }
    }

    private fun goneAnim() {
        binding.crashLeft.visibility = View.GONE
        binding.crashCenter.visibility = View.GONE
        binding.crashRight.visibility = View.GONE
    }

    private fun getMusic() {
        getPointMusic = MediaPlayer.create(this, R.raw.get_point)
        getPointMusic.start()
    }

    private fun failMusic() {
        failPointmusic = MediaPlayer.create(this, R.raw.fail_point)
        failPointmusic.start()
    }

    private fun gameOverMusic() {
        gameOverMus = MediaPlayer.create(this, R.raw.game_over)
        gameOverMus.start()
    }

    private fun failScoreFun() {
        when (failScore) {
            1 -> {
                binding.life1.playAnimation()
                handler.postDelayed({ binding.life1.visibility = gone }, 600)
            }
            2 -> {
                binding.life2.playAnimation()
                handler.postDelayed({ binding.life2.visibility = gone }, 600)
            }
            3 -> {
                binding.life3.playAnimation()
                handler.postDelayed({ binding.life3.visibility = gone }, 600)
                gameOver()
            }
            /*0 -> {
                life = true
                lifeTime()
            }*/
        }
    }

    private fun gameOver() {
        lost = false
        pauseGame()
        gameOverMusic()
        binding.gameOverAnim1.visibility = view
        binding.gameOverAnim1.playAnimation()
        handler.postDelayed({
            binding.gameOverAnim2.visibility = view
            binding.gameOverAnim2.playAnimation()
            binding.gameOverAnim1.visibility = View.GONE
            gameOverMusic = MediaPlayer.create(this,R.raw.laugh_lost)
            gameOverMusic.start()
            handler.postDelayed({
                binding.gameOverAnim2.visibility = View.GONE
                binding.gameOverAnimLast.visibility = view
                binding.gameOverAnimLast.playAnimation()
                binding.gameOverAnimLast.loop(true)
                binding.btnReplay.visibility = view
                binding.btnReplay.setOnClickListener {
                    binding.gameOverAnimLast.visibility = View.GONE
                    gameOverMusic.stop()
                    gameOverMus.stop()
                    binding.btnReplay.visibility = View.GONE
                    againGame()
                }
            }, 800)
        }, 1500)
    }

    private fun pauseGame() {
        viewTomchi(true)
        viewPaqir(true)
    }

    private fun resumeGame() {
        touchPos = 2
        viewPaqir(true)
        binding.paqirCenter.visibility = view
    }

    private fun levelWinMusic(){
        gameLevelWin = MediaPlayer.create(this,R.raw.level_win)
        gameLevelWin.start()
    }
}