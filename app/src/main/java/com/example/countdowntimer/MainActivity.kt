package com.example.countdowntimer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countdowntimer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            startButton.setOnClickListener {
                val userInput = numberInput.text.toString()

                if (userInput.isNotEmpty()) {
                    startClick(userInput)
                } else {
                    // Вывод сообщения об ошибке
                    Toast.makeText(
                        this@MainActivity,
                        "Поле ввода не должно быть пустым",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun startClick(number: String) {
        val i = Intent(this, Activity2::class.java)
        i.putExtra("number", number)
        startActivity(i)
    }
}

