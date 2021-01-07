package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.tarjeta_mensaje.view.*
import kotlinx.android.synthetic.main.tarjeta_xat.view.*
import java.util.*

class ListaXatAdapter(val c: Context, private val onEditListener: () -> Unit = {}) : RecyclerView.Adapter<ListaXatAdapter.HolderXat>() {
    private var ListaXats: ArrayList<String> = ArrayList()


    fun addPerson(name: String) {
        ListaXats.add(name)
        notifyItemInserted(ListaXats.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderXat {
        val v: View = LayoutInflater.from(c).inflate(R.layout.tarjeta_xat, parent, false)
        return HolderXat(v)
    }

    override fun getItemCount(): Int = ListaXats.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HolderXat, position: Int) {
        holder.itemView.userNameXat.text = ListaXats[position]
        holder.itemView.buttonDeleteXat.hint = ListaXats[position]
    }

    class HolderXat(view: View) : RecyclerView.ViewHolder(view) {

        fun getn(): TextView {
            return itemView.authorName
        }


    }


}