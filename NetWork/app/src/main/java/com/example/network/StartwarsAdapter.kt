package com.example.network

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class SWAdapter(
    val detector: OnSWHolderClickListener,
    val starWarChar: Array<SWChar>
) :
    RecyclerView.Adapter<SWAdapter.SWHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SWAdapter.SWHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder, parent, false)
        return SWHolder(itemView)
    }

    override fun getItemCount(): Int {
        return starWarChar.size
    }

    override fun onBindViewHolder(holder: SWHolder, position: Int) {
        val swChar = starWarChar[position]
        holder.binding(swChar)
        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v?.context, swChar.name, Toast.LENGTH_SHORT).show()
            detector.onClick(v, swChar)
        }
    }

    class SWHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val heightTextView: TextView = itemView.findViewById(R.id.heightTextView)
        val massTextView: TextView = itemView.findViewById(R.id.massTextView)

        fun binding(item: SWChar) {
            nameTextView.text = item.name
            heightTextView.text = "${item.height}"
            massTextView.text = "${item.mass}"
        }
    }

    interface OnSWHolderClickListener {
        fun onClick(v: View, item: SWChar)
    }
}
