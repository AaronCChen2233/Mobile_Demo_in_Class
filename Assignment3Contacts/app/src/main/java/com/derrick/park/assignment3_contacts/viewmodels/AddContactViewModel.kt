package com.derrick.park.assignment3_contacts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derrick.park.assignment3_contacts.models.Contact


class AddContactViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()

    val name: MutableLiveData<String>
        get() = _name

    private val _phoneNumber = MutableLiveData<String>()

    val phoneNumber: MutableLiveData<String>
        get() = _phoneNumber

    private val _isAddbtnEnable = MutableLiveData<Boolean>()

    val isAddbtnEnable: LiveData<Boolean>
        get() = _isAddbtnEnable

    private val _savedContact = MutableLiveData<Contact>()
    val savedContact: LiveData<Contact>
        get() = _savedContact

    init {
        _name.value = ""
        _phoneNumber.value = ""
    }

    fun onAdd() {
        _savedContact.value = Contact(name.value?.substringBefore(" ")!!, name.value?.substringAfter(" ")!!, phoneNumber.value!!)
        println()
    }

    fun onNameTextChange(s: CharSequence) {
        _isAddbtnEnable.value = checkName(s.toString()) && checkPhone(phoneNumber.value!!)
    }

    fun onPhoneNumberTextChange(s: CharSequence) {
        _isAddbtnEnable.value = checkName(name.value!!) && checkPhone(s.toString())
    }

    private fun checkName(name:String):Boolean{
        val regex = Regex(pattern = "([A-z])+\\ ([A-z])\\w+")
        val matched = regex.containsMatchIn(input = name)
        return matched
    }

    private fun checkPhone(phone:String):Boolean{
        return phone.length == 10
    }

}