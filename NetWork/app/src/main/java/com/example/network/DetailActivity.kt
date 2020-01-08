package com.example.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.network.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val name = intent.getStringExtra(MainActivity.NAME_EXTRA)
        val height = intent.getIntExtra(MainActivity.HEIGHT_EXTRA, 0)
        val mass = intent.getIntExtra(MainActivity.MASS_EXTRA, 0)

        binding.apply {
            nameTextView.text = name
            heightTextView.text = "Height: $height"
            massTextView.text = "Mass: $mass"
        }
    }
}
