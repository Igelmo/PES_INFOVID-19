package edu.upc.fib.pes_infovid19.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.tarjeta_mensaje.view.*
import java.util.*

class AdapterMensaje(val c: Context) : RecyclerView.Adapter<AdapterMensaje.Holdermensaje>() {

    private var ListaMensajes: MutableList<Mensaje> = ArrayList<Mensaje>()

    public fun addMessage(m: Mensaje) {
        ListaMensajes.add(m)
        notifyItemInserted(ListaMensajes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holdermensaje {
        var v: View = LayoutInflater.from(c).inflate(R.layout.tarjeta_mensaje, parent, false)
        return Holdermensaje(v)
    }

    override fun getItemCount(): Int = ListaMensajes.size

    override fun onBindViewHolder(holder: Holdermensaje, position: Int) {
        holder.getnombre().text = ListaMensajes[position].getnombre1()
        holder.getmensaje().text = ListaMensajes[position].getmensaje1()
        holder.gethora().text = ListaMensajes[position].getdata1()
    }

    class Holdermensaje(view: View) : RecyclerView.ViewHolder(view) {

        fun getnombre(): TextView {
            return itemView.authorName
        }

        fun getmensaje(): TextView {
            return itemView.textMessage
        }

        fun gethora(): TextView {
            return itemView.dateMessage
        }


    }

}