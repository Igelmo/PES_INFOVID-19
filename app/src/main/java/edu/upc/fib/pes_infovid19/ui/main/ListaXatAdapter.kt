package edu.upc.fib.pes_infovid19.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.tarjeta_mensaje.view.authorName
import kotlinx.android.synthetic.main.tarjeta_xat.view.*
import java.util.*

class ListaXatAdapter(val c: Context) : RecyclerView.Adapter<ListaXatAdapter.HolderXat>() {
    private var ListaXats: ArrayList<String> = ArrayList<String>()


    fun addPerson(name: String) {
        ListaXats.add(name)
        notifyItemInserted(ListaXats.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderXat {
        val v: View = LayoutInflater.from(c).inflate(R.layout.tarjeta_xat, parent, false)
        return HolderXat(v)
    }

    override fun getItemCount(): Int = ListaXats.size

    override fun onBindViewHolder(holder: HolderXat, position: Int) {
        holder.getn().text = ListaXats[position]
        holder.itemView.button.text = ListaXats[position]

    }

    class HolderXat(view: View) : RecyclerView.ViewHolder(view) {

        fun getn(): TextView {
            return itemView.authorName
        }


    }


}