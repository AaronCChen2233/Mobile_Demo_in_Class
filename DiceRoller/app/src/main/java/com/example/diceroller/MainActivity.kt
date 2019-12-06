package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var diceLabel:TextView
    lateinit var diceImage:ImageView
    lateinit var diceImage2:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById<ImageView>(R.id.diceImage)
        diceImage2 = findViewById<ImageView>(R.id.diceImage2)
    }

    fun rollButtonTapped(view: View) {
        /*get random number from 1 to 6*/
        var randomInt = Random().nextInt(6) + 1
        var img: Int = getImageFromInt(randomInt)
        diceImage.setImageResource(img)
        randomInt = Random().nextInt(6) + 1
        img = getImageFromInt(randomInt)
        diceImage2.setImageResource(img)

        /*set text*/
//        diceLabel.text = randomInt.toString()
//        Toast.makeText(this, "button click", Toast.LENGTH_LONG).show()
    }

    private fun getImageFromInt(randomInt: Int): Int {
        val img: Int = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }
        return img
    }
}
