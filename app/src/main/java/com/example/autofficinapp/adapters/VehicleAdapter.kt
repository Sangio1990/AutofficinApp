package com.example.autofficinapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autofficina.entities.Vehicle
import com.example.autofficinapp.R
import com.example.autofficinapp.services.ClientService

/**
 * Adapter per la visualizzazione dei lavori nella RecyclerView
 * @param vehicleList: List<Vehicle> -> Lista di veicoli da visualizzare
 * @return viewHolder: View -> La view da mostrare
 */
class VehicleAdapter(val vehicleList: List<Vehicle>) :
    RecyclerView.Adapter<VehicleAdapter.viewHolder>() {
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
    override fun getItemCount(): Int = vehicleList.size


    /**
     * Metodo che presenta UN singolo veicolo nella RecyclerView
     * @param holder -> La ViewHolder che contiene le due TextView
     * @param position -> La posizione del veicolo nella lista
     */
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val vehicle = vehicleList[position]
        val text = "${vehicle.brand} ${vehicle.model}: ${vehicle.plateNumber}"
        holder.leftTextView.text = text
        val carOwner = vehicle.clientId?.let { ClientService.getClient(it) }

        // Se il cliente non esiste, usa una stringa di default
        if (carOwner != null) {
            holder.rightTextView.text = "${carOwner.name} ${carOwner.surname}"
        } else {
            holder.rightTextView.text = "Proprietario non trovato"
        }
    }
}


