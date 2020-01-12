package com.derrick.park.assignment3_contacts.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.derrick.park.assignment3_contacts.viewmodels.AddContactViewModel

class AddContactViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return AddContactViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}