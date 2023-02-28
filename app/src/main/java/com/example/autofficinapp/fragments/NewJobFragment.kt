package com.example.autofficinapp.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.autofficina.entities.Job
import com.example.autofficinapp.R
import com.example.autofficinapp.services.JobService
import com.example.autofficinapp.services.VehicleService

/**
 * Fragment per l'aggiunta di un nuovo lavoro.
 */
class NewJobFragment : Fragment() {
    private lateinit var spinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate del layout per questo fragment
        val view = inflater.inflate(R.layout.fragment_new_job, container, false)
        spinner = view.findViewById(R.id.vehicle_spinner)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vehicleTextList = mutableListOf<String>()
        val vehicleList = VehicleService.getAllVehicles()
        if (vehicleList.size== 0){
            // Visualizza un messaggio Toast se non ci sono veicoli nel sistema
            Toast.makeText(context, "Per inserire un nuovo intervento devi prima inserire un veicolo", Toast.LENGTH_LONG).show()
            // Naviga verso il frammento per aggiungere un nuovo veicolo
            findNavController().navigate(R.id.action_newJobFragment_to_newVehicleFragment)
        }
        // Popola la lista di testo con i veicoli disponibili
        vehicleList.forEach {
            vehicleTextList.add("${it.brand} ${it.model}: ${it.plateNumber}")
        }

        // Inizializza lo Spinner con la lista di testo dei veicoli disponibili
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, vehicleTextList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        view.findViewById<Button>(R.id.add_new_job_button).setOnClickListener {
            val description =
                view.findViewById<EditText>(R.id.new_job_description_editText).text.toString()
            val workHours =
                view.findViewById<EditText>(R.id.new_job_hours_needed_editText).text.toString()
                    .toFloat()
            val startDate =
                view.findViewById<EditText>(R.id.new_job_start_date_editText).text.toString()
            val endDate =
                view.findViewById<EditText>(R.id.new_job_end_date_editText).text.toString()

            // Ottiene l'ID del veicolo selezionato nello Spinner
            val vehicle_id = spinner.selectedItemPosition
            val selectedVehicle = vehicleList[vehicle_id]
            // Aggiunge un nuovo lavoro nel servizio JobService
            JobService.addJob(
                Job(
                    0,
                    description,
                    workHours,
                    startDate,
                    endDate,
                    selectedVehicle.id
                )
            )
            // Naviga verso il frammento per visualizzare la lista dei lavori
            findNavController().navigate(R.id.action_newJobFragment_to_jobListFragment)
        }
    }
}
