package com.example.autofficinapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.autofficinapp.R

/**
 * Fragment che rappresenta la home page dell'applicazione.
 * Qui l'utente può accedere alle funzionalità principali dell'applicazione.
 */
class HomePageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo frammento
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    /**
     * Configura i bottoni per accedere alle varie funzionalità dell'applicazione.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Naviga alla schermata per la creazione di un nuovo cliente
        view.findViewById<Button>(R.id.new_client_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newClientFragment)
        }

        // Naviga alla schermata per la creazione di un nuovo veicolo
        view.findViewById<Button>(R.id.new_vehicle_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newVehicleFragment)
        }

        // Naviga alla schermata per la creazione di un nuovo lavoro
        view.findViewById<Button>(R.id.new_job_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newJobFragment)
        }
    }
}
