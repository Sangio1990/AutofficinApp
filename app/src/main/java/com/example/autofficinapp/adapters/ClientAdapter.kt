package com.example.autofficinapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autofficina.entities.Client
import com.example.autofficinapp.R
import java.util.*

/**
 * Adapter il cui compito è quello di gestire la visualizzazione dei dati nella RecyclerView
 * @param clientList: List<Client>  ->  Lista di persone
 * @return viewHolder: View -> La view da mostrare
 */
class ClientAdapter(val clientList: List<Client>):
    RecyclerView.Adapter<ClientAdapter.viewHolder>() {
    class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftTextView: TextView = view.findViewById(R.id.left_textview_element)
        val rightTextView: TextView = view.findViewById(R.id.right_textview_element)
    }

    companion object {
        private const val VIEW_TYPE_EVEN = 0
        private const val VIEW_TYPE_ODD = 1
    }
    /**
     * Metodo che crea una ViewHolder adatto ai dati passati scegliendo se
     * passare il layout light o dark
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context)
        val layoutResId = when (viewType) {
            VIEW_TYPE_EVEN -> R.layout.single_element_light
            VIEW_TYPE_ODD -> R.layout.single_element_dark
            else -> throw IllegalArgumentException("Invalid view type")
        }
        val itemView = view.inflate(layoutResId, parent, false)
        return viewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) VIEW_TYPE_EVEN else VIEW_TYPE_ODD
    }

    /**
     * @return Int -> Numero di elementi da presentare
     */
    override fun getItemCount(): Int = clientList.size


    /**
     * Metodo che ha il compito di presentare UN singolo dato
     * @param position -> Posizione dell'elemento nella lista
     * @param holder -> Il ViewHolder preparato da onCreateViewHolder
     */
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val client = clientList[position]
        // Rendo il primo carattere del nome e del cognome in uppercase
        val fullName = "${
            client.name.lowercase().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        } ${
            client.surname.lowercase().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }"
        holder.leftTextView.text = fullName
        holder.rightTextView.text = client.email
    }
}