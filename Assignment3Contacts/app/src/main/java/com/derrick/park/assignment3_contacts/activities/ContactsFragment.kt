package com.derrick.park.assignment3_contacts.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.android.jmaxime.adapters.RecyclerAdapter
import com.android.jmaxime.adapters.decorators.SectionedAdapter
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.databinding.FragmentContactsBinding
import com.derrick.park.assignment3_contacts.models.Contact
import com.derrick.park.assignment3_contacts.models.ContactList
import com.derrick.park.assignment3_contacts.network.ContactClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//import java.util.*

const val SAVED_CONTACTS_KEY = "saved_contacts_key"

class ContactsFragment : Fragment() {
    private var mContactList: ArrayList<Contact> = arrayListOf<Contact>()
    private var adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContactList = savedInstanceState?.getParcelableArrayList<Contact>(SAVED_CONTACTS_KEY) ?: arrayListOf<Contact>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentContactsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contacts, container, false)
        binding.setLifecycleOwner(this)

        binding.contactsRecyclerView.adapter = adapter

        binding.addfloatingActionButton.setOnClickListener {

            val contactArray = arrayOfNulls<Contact>(mContactList.size)
            mContactList.toArray(contactArray)

            val action = ContactsFragmentDirections.actionContactsFragmentToAddContactFragment(contactArray)
            action.contacts = contactArray
            NavHostFragment.findNavController(this).navigate(action)
        }

        val contactArray = arguments?.getParcelableArray("contact")
        if (contactArray!=null){
            mContactList = contactArray.toCollection(ArrayList()) as ArrayList<Contact>
        }

        if(mContactList.size == 0){
        val call = ContactClient.getContacts(10)
        call.enqueue(object : Callback<ContactList> {
            override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) {
                if (response.isSuccessful) {
                    mContactList.addAll(response.body()!!.contactList)
                    adapter.addHeaderAndSubmitList(mContactList)
                }
            }

            override fun onFailure(call: Call<ContactList>, t: Throwable) { // Error Handling
            }
        })}
        else{
            adapter.addHeaderAndSubmitList(mContactList)
        }

        return binding.root
    }

//    fun addNewContact(newContact:Contact?){
//        if(newContact!=null) {
//            mContactList.add(newContact)
//            adapter.notifyDataSetChanged()
//        }
//    }
}
