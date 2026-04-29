package com.example.dicexml

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDice1: ImageView = findViewById(R.id.iv_dice_1)
        val ivDice2: ImageView = findViewById(R.id.iv_dice_2)
        val btnRoll: Button = findViewById(R.id.btn_roll)
        val tvStatus: TextView = findViewById(R.id.tv_status)

        btnRoll.setOnClickListener {
            val roll1 = (1..6).random()
            val roll2 = (1..6).random()

            ivDice1.setImageResource(getDiceResource(roll1))
            ivDice2.setImageResource(getDiceResource(roll2))

            tvStatus.visibility = View.VISIBLE
            if (roll1 == roll2) {
                tvStatus.text = getString(R.string.status_double)
            } else {
                tvStatus.text = getString(R.string.status_not_lucky)
            }
        }
    }

    private fun getDiceResource(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
}