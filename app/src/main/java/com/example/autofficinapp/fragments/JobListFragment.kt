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
 * A simple [Fragment] subclass.
 * Use the [JobListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JobListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Popolo la listview con i dati nel database
        view.findViewById<RecyclerView>(R.id.job_recyclerView).apply {
            layoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            adapter = JobAdapter(JobService.getAllJobs())
        }
    }
}