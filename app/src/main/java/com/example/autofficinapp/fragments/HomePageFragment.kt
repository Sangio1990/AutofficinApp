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
 * A simple [Fragment] subclass.
 * Use the [HomePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.new_client_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newClientFragment)
        }

        view.findViewById<Button>(R.id.new_vehicle_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newVehicleFragment)
        }

        view.findViewById<Button>(R.id.new_job_button).setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_newJobFragment)
        }
    }
}