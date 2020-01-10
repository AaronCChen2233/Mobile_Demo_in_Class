package com.derrick.park.assignment3_contacts.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.derrick.park.assignment3_contacts.databinding.ContactsListItemBinding
import com.derrick.park.assignment3_contacts.models.Contact

class ContactAdapter : ListAdapter<Contact, ContactAdapter.ContactHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ContactHolder {
        return ContactHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ContactHolder private constructor(val binding: ContactsListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.nameTextView

        fun bind(item: Contact) {
            binding.contact = item
            name.text = item.name.toString()
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ContactHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContactsListItemBinding.inflate(layoutInflater, parent, false)
                return ContactHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.equals(newItem)
    }
}

