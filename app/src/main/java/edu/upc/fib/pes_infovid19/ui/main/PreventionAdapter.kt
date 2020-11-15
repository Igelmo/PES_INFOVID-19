package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class PreventionAdapter(val preventions: List<Prevention>, private val isAdmin: Boolean) : RecyclerView.Adapter<PreventionAdapter.ViewHolder>() {
    private var expandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PreventionAdapter.ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        holder.bind(preventions[position], isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = preventions.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(prevention: Prevention, isExpanded: Boolean, admin: Boolean) {
            if (!admin) {
                itemView.titledropdown.text = prevention.title
                itemView.textdropdown.text = prevention.text
                itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
                itemView.textdropdown.isVisible = isExpanded

            }
            itemView.editButton.isVisible = admin
            itemView.deleteButton.isVisible = admin
            itemView.arrowDropDown.isVisible = !admin
            itemView.textdropdown.isVisible = !admin && isExpanded
        }
    }
}