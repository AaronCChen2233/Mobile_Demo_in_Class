package com.derrick.park.assignment3_contacts.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.derrick.park.assignment3_contacts.factorys.AddContactViewModelFactory
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.viewmodels.AddContactViewModel
import com.derrick.park.assignment3_contacts.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment() {

    private lateinit var viewModel: AddContactViewModel
    private lateinit var viewModelFactory: AddContactViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentAddContactBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_contact, container, false)

        viewModelFactory = AddContactViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddContactViewModel::class.java)

        viewModel.savedContact.observe(this, Observer { playAgain ->
            val action = AddContactFragmentDirections.actionAddContactFragmentToContactsFragment(playAgain)
            action.contact = playAgain
            NavHostFragment.findNavController(this).navigate(action)
        })

        binding.addContactViewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}
