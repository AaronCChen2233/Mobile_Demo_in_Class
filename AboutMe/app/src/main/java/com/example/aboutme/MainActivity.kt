package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Aaron")
    //    lateinit var editText: EditText
//    lateinit var nicknameTextView: TextView
//    lateinit var doneButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
//        editText = findViewById<EditText>(R.id.nickname_edit)
//        nicknameTextView = findViewById<TextView>(R.id.nickname_text)
//        doneButton = findViewById<Button>(R.id.done_button)
    }

    fun clickHandlerFunction(view: View) {
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            invalidateAll()
        }

    }

    fun updateNickname(view: View) {
        binding.apply {
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            view.visibility = View.GONE
            nicknameEdit.requestFocus()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }
}
