package com.derrick.park.assignment3_contacts.activities

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.derrick.park.assignment3_contacts.factorys.AddContactViewModelFactory
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.viewmodels.AddContactViewModel
import com.derrick.park.assignment3_contacts.databinding.FragmentAddContactBinding
import com.derrick.park.assignment3_contacts.models.Contact

class AddContactFragment : Fragment() {

    private lateinit var viewModel: AddContactViewModel
    private lateinit var viewModelFactory: AddContactViewModelFactory
    private var mContactList: ArrayList<Contact> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentAddContactBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_contact, container, false)

        viewModelFactory = AddContactViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddContactViewModel::class.java)

        viewModel.savedContact.observe(this, Observer { addContact ->

            mContactList.add(addContact)

            val contactArray = arrayOfNulls<Contact>(mContactList.size)
            mContactList.toArray(contactArray)

            val action = AddContactFragmentDirections.actionAddContactFragmentToContactsFragment(contactArray)
            action.contact = contactArray
            this.findNavController().navigate(action)

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        })

        val contactArray = arguments?.getParcelableArray("contacts")
        if (contactArray!=null){
            mContactList = contactArray.toCollection(ArrayList()) as ArrayList<Contact>
        }

        binding.addContactViewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}
