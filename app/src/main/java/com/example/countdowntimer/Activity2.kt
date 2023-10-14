package com.example.countdowntimer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.countdowntimer.databinding.Activity2Binding


//    private fun acceptData() {
//        val textView = binding.textView
//
//        val receivedStringNumber = intent.getStringExtra("number")
//        if (receivedStringNumber != null) {
//            textView.text = "$receivedStringNumber"
//        }
//    }

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    private var myCountDownTimer: MyCountDownTimer? = null
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        textView = findViewById(R.id.textView)

        acceptData()

        with(binding) {
            startButton.setOnClickListener() {
                myCountDownTimer?.start()
            }
            pauseButton.setOnClickListener() {
                myCountDownTimer?.cancel()
            }
            resetButton.setOnClickListener() {
                myCountDownTimer?.reset()
            }
        }
    }

    private fun acceptData() {
        val receivedStringNumber = intent.getStringExtra("number")
        if (receivedStringNumber != null) {
            val seconds = receivedStringNumber.toLong()

            myCountDownTimer = MyCountDownTimer(
                totalTime = seconds * 1000,
                interval = 1000,
                onTick = { millisUntilFinished ->
                    textView.text = (millisUntilFinished / 1000).toString()
                },
                onFinish = {
                    textView.text = "Countdown Finished!"
                }
            )
        }
    }
}


class MyCountDownTimer(
    private val totalTime: Long,
    private val interval: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit
) {
    private var timeRemaining: Long = totalTime
    private var isRunning: Boolean = false
    private var initialTotalTime: Long = totalTime

    fun start() {
        if (!isRunning) {
            isRunning = true
            tick()
        }
    }

    fun cancel() {
        isRunning = false
    }

    fun reset() {
        isRunning = false
        timeRemaining = initialTotalTime
        onTick(timeRemaining)
    }

    private fun tick() {
        if (isRunning) {
            onTick(timeRemaining)

            if (timeRemaining <= 0) {
                onFinish()
                isRunning = false
            } else {
                timeRemaining -= interval
                Handler(Looper.getMainLooper()).postDelayed({
                    tick()
                }, interval)
            }
        }
    }
}







