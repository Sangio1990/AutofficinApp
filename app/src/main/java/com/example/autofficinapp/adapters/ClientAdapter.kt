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
 * Adapter per la visualizzazione dei lavori nella RecyclerView
 * @param clientList: List<Client> -> Lista di clienti da visualizzare
 * @return viewHolder: View -> La view da mostrare
 */
class ClientAdapter(val clientList: List<Client>):
    RecyclerView.Adapter<ClientAdapter.viewHolder>() {
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
    override fun getItemCount(): Int = clientList.size


    /**
     * Metodo che presenta UN singolo cliente nella RecyclerView
     * @param holder -> La ViewHolder che contiene le due TextView
     * @param position -> La posizione del cleinte nella lista
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