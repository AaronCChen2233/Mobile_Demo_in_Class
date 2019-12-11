package com.example.colormyview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        val boxOneText = findViewById<TextView>(R.id.textView)
        val boxTwoText = findViewById<TextView>(R.id.textView2)
        val boxThreeText = findViewById<TextView>(R.id.textView3)
        val boxFourText = findViewById<TextView>(R.id.textView4)
        val boxFiveText = findViewById<TextView>(R.id.textView5)
        val rootConstraintLayout = findViewById<View>(R.id.constraint_layout)
        val redButton = findViewById<TextView>(R.id.button2)
        val greenButton = findViewById<TextView>(R.id.button3)
        val yellowButton = findViewById<TextView>(R.id.button4)
        val clickableViews: List<View> =
            listOf(
                boxOneText, boxTwoText, boxThreeText,
                boxFourText, boxFiveText, rootConstraintLayout,
                redButton, greenButton, yellowButton
            )

        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }
    }

    private fun makeColored(view: View) {
        val boxThreeText = findViewById<TextView>(R.id.textView3)
        val boxFourText = findViewById<TextView>(R.id.textView4)
        val boxFiveText = findViewById<TextView>(R.id.textView5)

        when (view.id) {
            R.id.textView -> view.setBackgroundColor(Color.DKGRAY)
            R.id.textView2 -> view.setBackgroundColor(Color.GRAY)
            R.id.textView3 -> view.setBackgroundColor(Color.BLUE)
            R.id.textView4 -> view.setBackgroundColor(Color.MAGENTA)
            R.id.textView5 -> view.setBackgroundColor(Color.BLUE)
            R.id.button2 -> boxThreeText.setBackgroundResource(R.color.my_red)
            R.id.button3 -> boxFourText.setBackgroundResource(R.color.my_yellow)
            R.id.button4 -> boxFiveText.setBackgroundResource(R.color.my_green)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
