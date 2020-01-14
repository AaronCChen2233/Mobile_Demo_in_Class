package com.derrick.park.assignment3_contacts.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.derrick.park.assignment3_contacts.databinding.ContactsListItemBinding
import com.derrick.park.assignment3_contacts.databinding.ContactsListSectionItemBinding
import com.derrick.park.assignment3_contacts.models.Contact
import com.derrick.park.assignment3_contacts.models.DataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1
private val adapterScope = CoroutineScope(Dispatchers.Default)

class ContactAdapter : ListAdapter<DataItem,RecyclerView.ViewHolder>(SleepNightDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TitleHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ContactHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContactHolder -> {
                val item = getItem(position) as DataItem.ContactItem
                holder.bind(item.contact)
            }
            is TitleHolder -> {
                val item = getItem(position) as DataItem
                holder.bind(item)
            }
        }
    }

    class TitleHolder constructor(val binding: ContactsListSectionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.title = item
            binding.headerTextView.text = item.headerString
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TitleHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContactsListSectionItemBinding.inflate(layoutInflater, parent, false)
                return TitleHolder(binding)
            }
        }
    }

    fun addHeaderAndSubmitList(list: ArrayList<Contact>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header(""))
                else ->
                {
                    val temp: ArrayList<DataItem> = list.map { DataItem.ContactItem(it) }.toCollection(ArrayList())
                    temp.sortWith(compareBy (String.CASE_INSENSITIVE_ORDER, { it.headerString }))

                    /**get firs Word*/
                    val stringList :ArrayList<String> = list.map{it.name.toString().get(0).toString().toUpperCase()}.distinct()
                            .toCollection(ArrayList())

                    stringList.forEach{s->
                        temp.add(temp.indexOf(temp.first { it.headerString.get(0).toString().toUpperCase()==s }), DataItem.Header(s))
                    }
                    temp
                }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ContactItem -> ITEM_VIEW_TYPE_ITEM
            else -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class ContactHolder private constructor(val binding: ContactsListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.nameTextView
        val phone :TextView = binding.phoneTextView

        fun bind(item: Contact) {
            binding.contact = item
            name.text = item.name.toString()
            phone.text = item.cell.toString()
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

class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.equals(newItem)
    }
}

