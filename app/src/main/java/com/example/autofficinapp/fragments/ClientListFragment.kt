package com.example.autofficinapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autofficinapp.R
import com.example.autofficinapp.adapters.ClientAdapter
import com.example.autofficinapp.services.ClientService
import java.util.concurrent.Executors

/**
 * Questo Fragment rappresenta la schermata di visualizzazione della lista dei clienti presenti nel database.
 *
 * Per popolare la RecyclerView con la lista dei clienti, viene utilizzato un adapter personalizzato (ClientAdapter)
 * che utilizza la lista di lavori fornita dal ClientService.
 *
 * Utilizza il layout 'fragment_client_list.xml' per visualizzare la lista dei clienti.
 */
class ClientListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo frammento
        return inflater.inflate(R.layout.fragment_client_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Popola la recycler view con i dati dal database
        view.findViewById<RecyclerView>(R.id.client_recyclerView).apply {
            // Inizializza il layout della recycler view indicandogli che deve essere un LinearLayoutManager
            // con orientamento verticale
            layoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            // Inizializzo l'adapter della recycler view indicandogli che deve essere
            // un'istanza dell'adapter creato ad hoc per popolare questa vista
            adapter = ClientAdapter(ClientService.getAllClients())
        }
    }
}