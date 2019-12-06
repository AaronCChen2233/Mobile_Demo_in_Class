package com.example.colorquiz

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var leftButton: Button
    lateinit var rightButton: Button
    lateinit var questionText: TextView
    lateinit var scoreText: TextView
    var toast: Toast? = null

    /*color will chosen in this map*/
    var androidColors = mapOf(
        "BLACK" to Color.BLACK,
        "DKGRAY" to Color.DKGRAY,
        "GRAY" to Color.GRAY,
        "LTGRAY" to Color.LTGRAY,
        "WHITE" to Color.WHITE,
        "RED" to Color.RED,
        "GREEN" to Color.GREEN,
        "BLUE" to Color.BLUE,
        "YELLOW" to Color.YELLOW,
        "CYAN" to Color.CYAN,
        "MAGENTA" to Color.MAGENTA
    )

    /*0 is left correct ,1 is right correct*/
    var answer = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        questionText = findViewById(R.id.question)
        scoreText = findViewById(R.id.score)
        scoreText.text = score.toString()
        generatQuestion()
    }

    fun generatQuestion() {
        var correctColorIndex = 0
        var wrongColorIndex = 0
        /*question and wrong must be different*/
        do {
            correctColorIndex = Random().nextInt(androidColors.size)
            wrongColorIndex = Random().nextInt(androidColors.size)
        } while (correctColorIndex == wrongColorIndex)

        val correctColorint = ArrayList(androidColors.values).get(correctColorIndex)
        val wrongColorint = ArrayList(androidColors.values).get(wrongColorIndex)
        val correctColorName = ArrayList(androidColors.keys).get(correctColorIndex)

        answer = Random().nextInt(2)
        if (answer == 0) {
            leftButton.setBackgroundColor(correctColorint)
            rightButton.setBackgroundColor(wrongColorint)
        } else {
            rightButton.setBackgroundColor(correctColorint)
            leftButton.setBackgroundColor(wrongColorint)
        }

        questionText.text = correctColorName
    }

    fun leftButtonClick(view: View) {
        if (answer == 0) {
            /*correct*/
            showToast("Correct!")
            score++
        } else {
            /*wrong*/
            showToast("Wrong!")
            score--
        }
        scoreText.text = score.toString()
        generatQuestion()
    }

    fun rightButtonClick(view: View) {
        if (answer != 0) {
            /*correct*/
            showToast("Correct!")
            score++
        } else {
            /*wrong*/
            showToast("Wrong!")
            score--
        }
        scoreText.text = score.toString()
        generatQuestion()
    }

    private fun showToast(message:String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}
