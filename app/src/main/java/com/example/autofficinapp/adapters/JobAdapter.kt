package com.example.autofficinapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autofficina.entities.Job
import com.example.autofficinapp.R
import com.example.autofficinapp.services.ClientService
import com.example.autofficinapp.services.VehicleService

/**
 * Adapter per la visualizzazione dei lavori nella RecyclerView
 * @param jobList: List<Job> -> Lista di lavori da visualizzare
 * @return viewHolder: View -> La view da mostrare
 */
class JobAdapter(val jobList: List<Job>) :
    RecyclerView.Adapter<JobAdapter.viewHolder>() {
    /**
     * ViewHolder che contiene le due TextView per visualizzare i dati
     */
    class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftTextView: TextView = view.findViewById(R.id.left_textview_element)
        val rightTextView: TextView = view.findViewById(R.id.right_textview_element)
    }

    companion object {
        private const val VIEW_TYPE_EVEN = 0
        private const val VIEW_TYPE_ODD = 1
    }

    /**
     * Metodo che crea una ViewHolder adatta ai dati passati
     * @param parent -> Il ViewGroup in cui viene visualizzata la RecyclerView
     * @param viewType -> Il tipo di view da creare, pari o dispari
     * @return ViewHolder -> La ViewHolder creata
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context)
        // Cambia il layout in base a se la riga è pari o dispari
        val layoutResId = when (viewType) {
            VIEW_TYPE_EVEN -> R.layout.single_element_light
            VIEW_TYPE_ODD -> R.layout.single_element_dark
            else -> throw IllegalArgumentException("Invalid view type")
        }
        val itemView = view.inflate(layoutResId, parent, false)
        return viewHolder(itemView)
    }

    /**
     * Metodo che calcola quale tipo di view deve venire applicata e
     * restituisce se la riga di visualizzazione è pari o dispari
     * @param position -> La posizione dell'elemento nella lista
     * @return Int -> Il tipo di view, pari o dispari
     */
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) VIEW_TYPE_EVEN else VIEW_TYPE_ODD
    }

    /**
     * Metodo che restituisce il numero di elementi da visualizzare
     * @return Int -> Il numero di elementi da visualizzare
     */
    override fun getItemCount(): Int = jobList.size


    /**
     * Metodo che presenta UN singolo lavoro nella RecyclerView
     * @param holder -> La ViewHolder che contiene le due TextView
     * @param position -> La posizione del lavoro nella lista
     */
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val job = jobList[position]
        var text = "${job.startDate}\nTempo: ${job.workHours}h"
        holder.rightTextView.text = text

        // Recupera il veicolo e il proprietario del veicolo associati al lavoro
        val vehicle = job.carId?.let { VehicleService.getVehicle(it) }
        val carOwner = vehicle?.clientId?.let { ClientService.getClient(it) }

        // Se il veicolo non esiste, usa una stringa di default
        if (vehicle != null) {
            text = "${vehicle.brand} ${vehicle.model} ${vehicle.plateNumber}"
        } else {
            text = "Proprietario non trovato"
        }

        // Se il cliente non esiste, evito di inserire il dato
        if (carOwner != null) {
            text += "\n${carOwner.name} ${carOwner.surname}"
        }
        holder.leftTextView.text = text
    }
}


