package com.backendpapa.fightertimepractise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
//    Initialize the view
    private  lateinit var gameScoreView:TextView
    private lateinit var gameTimeView:TextView
    private lateinit var tapMeButton:TextView
    private  var timeleft=60

//    Game variables
    private var gameStarted=false
    private var initialCountdownTime:Long=60000
    private var countdownInterval:Long=1000
    private var score=0

    private lateinit var countDownTimer:CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        gameScoreView=findViewById(R.id.game_score_id)
        gameTimeView=findViewById(R.id.time_left_id)
        tapMeButton=findViewById(R.id.tap_me_id)
        resetGame()
        tapMeButton.setOnClickListener{
            if(!gameStarted){
                startGame()

            }
            increment()
        }

    }
    private fun increment(){
        score++
        gameScoreView.text=getString(R.string.your_score,score)
    }
    private fun resetGame(){
        score=0



        val initialScore=getString(R.string.your_score,score)
        gameScoreView.text=initialScore

        val initialTime=getString(R.string.time_left,60)
        gameTimeView.text=initialTime

        countDownTimer=object:CountDownTimer(initialCountdownTime,countdownInterval){
            override fun onTick(millisUntilFinished: Long) {
                timeleft=millisUntilFinished.toInt()/1000
                gameTimeView.text=getString(R.string.time_left,timeleft)
            }

            override fun onFinish() {
                endGame()
            }

        }
    }
    private fun endGame(){
        Toast.makeText(this,getString(R.string.game_over,score),Toast.LENGTH_LONG).show()
        score=0
        timeleft=60
        gameStarted=false
        gameScoreView.text=getString(R.string.your_score,score)
        gameTimeView.text=getString(R.string.time_left,timeleft)

    }
    private fun startGame(){
        countDownTimer.start()
        gameStarted=true
    }
}