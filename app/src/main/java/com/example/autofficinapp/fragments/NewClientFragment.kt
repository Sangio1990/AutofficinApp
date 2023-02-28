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
 * Fragment per l'aggiunta di un nuovo cliente.
 */
class NewClientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo fragment
        return inflater.inflate(R.layout.fragment_new_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aggiunge il listener per il pulsante "Aggiungi nuovo cliente"
        view.findViewById<Button>(R.id.add_new_client_button).setOnClickListener {
            // Legge i valori inseriti dall'utente
            val name = view.findViewById<EditText>(R.id.new_client_name_editText).text.toString()
            val surname = view.findViewById<EditText>(R.id.new_client_surname_editText).text.toString()
            val phone = view.findViewById<EditText>(R.id.new_client_phone_editText).text.toString()
            val email = view.findViewById<EditText>(R.id.new_client_mail_editText).text.toString()

            // Crea un nuovo oggetto Client con i dati inseriti dall'utente
            val newClient = Client(0, name, surname, phone, email)

            // Aggiunge il nuovo cliente al database tramite il servizio ClientService
            ClientService.addClient(newClient)

            // Torna alla schermata di visualizzazione della lista dei clienti
            findNavController().navigate(R.id.action_newClientFragment_to_clientListFragment)
        }
    }
}