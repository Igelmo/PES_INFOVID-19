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

class Adapter_Mensaje(val c: Context) : RecyclerView.Adapter<Adapter_Mensaje.Holder_mensaje>() {

    private var ListaMensajes: MutableList<Mensaje> = ArrayList<Mensaje>()

    public fun Add_mensajes(m: Mensaje) {
        ListaMensajes.add(m)
        notifyItemInserted(ListaMensajes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_Mensaje.Holder_mensaje {
        var v: View = LayoutInflater.from(c).inflate(R.layout.tarjeta_mensaje, parent, false)
        return Holder_mensaje(v)
    }

    override fun getItemCount(): Int {
        return ListaMensajes.size
    }

    override fun onBindViewHolder(holder: Adapter_Mensaje.Holder_mensaje, position: Int) {
        holder.getnombre().setText(ListaMensajes.get(position).getnombre())
        holder.getmensaje().setText(ListaMensajes.get(position).getmensaje())
        holder.gethora().setText(ListaMensajes.get(position).gethora())
    }

    class Holder_mensaje(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var nombre: TextView
        lateinit var mensaje: TextView
        lateinit var hora: TextView
        fun holdermensaje(view: View) {
            super.itemView

        }

        fun getnombre(): TextView {
            return itemView.authorName
        }

        fun getmensaje(): TextView {
            return itemView.textMessage
        }

        fun gethora(): TextView {
            return itemView.dateMessage
        }

        fun setnombre(nombre: TextView) {
            this.nombre = nombre
        }

        fun setmensaje(mensaje: TextView) {
            this.mensaje = mensaje
        }

        fun sethora(hora: TextView) {
            this.hora = hora
        }

    }

}