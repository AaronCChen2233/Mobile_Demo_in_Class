package com.derrick.park.assignment3_contacts.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.databinding.FragmentContactsBinding
import com.derrick.park.assignment3_contacts.models.Contact
import com.derrick.park.assignment3_contacts.models.ContactList
import com.derrick.park.assignment3_contacts.network.ContactClient
import com.derrick.park.assignment3_contacts.viewmodels.AddContactViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import java.util.*

class ContactsFragment : Fragment() {
    private var mContactList: ArrayList<Contact>? = null
    private var adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentContactsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contacts, container, false)
        binding.setLifecycleOwner(this)

        binding.contactsRecyclerView.adapter = adapter

        binding.addfloatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }
        val call = ContactClient.getContacts(10)

        call.enqueue(object : Callback<ContactList> {
            override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) {
                if (response.isSuccessful) {
                    mContactList = response.body()!!.contactList

                    adapter.submitList(mContactList)
                }
            }

            override fun onFailure(call: Call<ContactList>, t: Throwable) { // Error Handling
            }
        })

        return binding.root
    }


    fun addNewContact(newContact:Contact){
        mContactList?.add(newContact)
        adapter.notifyDataSetChanged()
    }
}
