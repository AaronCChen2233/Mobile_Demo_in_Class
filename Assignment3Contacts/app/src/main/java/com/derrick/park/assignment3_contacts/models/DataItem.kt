package com.derrick.park.assignment3_contacts.models

sealed class DataItem {
    abstract val headerString: String
    data class ContactItem(val contact: Contact): DataItem()      {
        override val headerString = contact.name.toString()
    }

    data class Header(val header:String): DataItem() {
        override val headerString = header
    }
}