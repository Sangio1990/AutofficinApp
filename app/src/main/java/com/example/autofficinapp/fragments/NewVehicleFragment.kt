package com.example.autofficinapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.R
import com.example.autofficinapp.services.ClientService
import com.example.autofficinapp.services.VehicleService


/**
 * Fragment per l'aggiunta di un nuovo veicolo.
 */
class NewVehicleFragment : Fragment() {
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo fragment
        val view = inflater.inflate(R.layout.fragment_new_vehicle, container, false)
        spinner = view.findViewById(R.id.client_spinner)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera la lista dei nomi dei clienti e dei clienti stessi
        val clientNamesList = mutableListOf<String>()
        val clientList = ClientService.getAllClients()

        // Se non ci sono clienti, mostra un Toast all'utente e naviga alla pagina per l'aggiunta di un nuovo cliente
        if (clientList.size == 0){
            Toast.makeText(context, "Per inserire un nuovo veicolo devi prima inserire un nuovo cliente!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newVehicleFragment_to_newClientFragment)
        }

        // Aggiunge i nomi dei clienti alla lista di nomi
        clientList.forEach {
            clientNamesList.add("${it.name} ${it.surname}")
        }

        // Popola lo spinner con la lista di nomi dei clienti
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, clientNamesList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // Aggiunge l'ascoltatore al bottone "Aggiungi"
        view.findViewById<Button>(R.id.add_new_vehicle_button).setOnClickListener {
            // Recupera i valori dei campi di input dell'utente
            val brand = view.findViewById<EditText>(R.id.new_vehicle_brand_editText).text.toString()
            val chassis =
                view.findViewById<EditText>(R.id.new_vehicle_chassis_editText).text.toString()
            val model = view.findViewById<EditText>(R.id.new_vehicle_model_editText).text.toString()
            val plateNumber =
                view.findViewById<EditText>(R.id.new_vehicle_plate_number_editText).text.toString()

            // Recupera l'id del cliente selezionato nello spinner e il cliente stesso
            val client_id = spinner.selectedItemPosition
            val selectedClient = clientList[client_id]

            // Aggiunge un nuovo veicolo con i valori recuperati e naviga alla pagina dei veicoli
            VehicleService.addVehicle(
                Vehicle(
                    0,
                    chassis,
                    brand,
                    model,
                    plateNumber,
                    selectedClient.id
                )
            )
            findNavController().navigate(R.id.action_newVehicleFragment_to_vehicleListFragment)
        }
    }
}
