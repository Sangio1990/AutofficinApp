package com.example.autofficinapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autofficinapp.R
import com.example.autofficinapp.adapters.JobAdapter
import com.example.autofficinapp.services.JobService

/**
 * Questo Fragment rappresenta la schermata di visualizzazione della lista dei lavori presenti nel database.
 *
 * Per popolare la RecyclerView con la lista dei lavori, viene utilizzato un adapter personalizzato (JobAdapter)
 * che utilizza la lista di lavori fornita dal JobService.
 *
 * Utilizza il layout 'fragment_job_list.xml' per visualizzare la lista dei lavori.
 */
class JobListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo frammento
        return inflater.inflate(R.layout.fragment_job_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Popola la recycler view con i dati dal database
        view.findViewById<RecyclerView>(R.id.job_recyclerView).apply {
            // Inizializza il layout della RecyclerView indicandogli che deve essere un LinearLayoutManager
            // con orientamento verticale
            layoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            // Inizializzo l'adapter della recycler view indicandogli che deve essere
            // un'istanza dell'adapter creato ad hoc per popolare questa vista
            adapter = JobAdapter(JobService.getAllJobs())
        }
    }
}
