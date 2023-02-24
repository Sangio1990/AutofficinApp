package com.example.autofficinapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.autofficina.entities.Client
import com.example.autofficinapp.R
import com.example.autofficinapp.services.ClientService

/**
 * A simple [Fragment] subclass.
 * Use the [NewClientFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewClientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.add_new_client_button).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.new_client_name_editText).text.toString()
            val surname =
                view.findViewById<EditText>(R.id.new_client_surname_editText).text.toString()
            val phone = view.findViewById<EditText>(R.id.new_client_phone_editText).text.toString()
            val email = view.findViewById<EditText>(R.id.new_client_mail_editText).text.toString()

            ClientService.addClient(Client(0, name, surname, phone, email))

            findNavController().navigate(R.id.action_newClientFragment_to_clientListFragment)
        }
    }
}