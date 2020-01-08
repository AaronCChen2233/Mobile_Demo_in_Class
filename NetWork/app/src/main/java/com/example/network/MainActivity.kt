package com.example.network

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.response
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SWAdapter.OnSWHolderClickListener {

    companion object {
        const val NAME_EXTRA = "NAME"
        const val HEIGHT_EXTRA = "HEIGHT"
        const val MASS_EXTRA = "MASS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val swRecycleView: RecyclerView = findViewById(R.id.swRecycleView)

        Fuel.get("https://swapi.co/api/people")
            .responseObject(Data.Deserializer()) { request, response, result ->
                val (data, _) = result
                data?.let {
                    val swChar = it.results
                    swRecycleView.adapter = SWAdapter(this, swChar)
                    swRecycleView.layoutManager = LinearLayoutManager(this)
                }
            }
    }

    override fun onClick(v: View, item: SWChar) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(NAME_EXTRA, item.name)
        intent.putExtra(HEIGHT_EXTRA, item.height)
        intent.putExtra(MASS_EXTRA, item.mass)

        startActivity(intent)
    }
}
