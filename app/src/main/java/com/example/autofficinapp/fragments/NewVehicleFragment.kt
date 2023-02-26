package com.example.autofficinapp.fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.autofficina.entities.Client
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.R
import com.example.autofficinapp.services.ClientService
import com.example.autofficinapp.services.VehicleService


/**
 * A simple [Fragment] subclass.
 * Use the [NewVehicleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewVehicleFragment : Fragment() {
    private lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_vehicle, container, false)
        spinner = view.findViewById(R.id.client_spinner)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientNamesList = mutableListOf<String>()
        val clientList = ClientService.getAllClients()
        if (clientList.size == 0){
            val toast = Toast.makeText(context, "Per inserire un nuovo veicolo devi prima inserire un nuovo cliente!", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM,0,0)
            toast.show()
            findNavController().navigate(R.id.action_newVehicleFragment_to_newClientFragment)
        }
        clientList.forEach {
            clientNamesList.add("${it.name} ${it.surname}")
        }

        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, clientNamesList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        view.findViewById<Button>(R.id.add_new_vehicle_button).setOnClickListener {
            val brand = view.findViewById<EditText>(R.id.new_vehicle_brand_editText).text.toString()
            val chassis =
                view.findViewById<EditText>(R.id.new_vehicle_chassis_editText).text.toString()
            val model = view.findViewById<EditText>(R.id.new_vehicle_model_editText).text.toString()
            val plateNumber =
                view.findViewById<EditText>(R.id.new_vehicle_plate_number_editText).text.toString()

            val client_id = spinner.selectedItemPosition
            val selectedClient = clientList[client_id]
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