package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class PreventionAdapter(
    val preventions: List<Prevention>, private val isAdmin: Boolean, private val onEditListener: (Prevention) -> Unit = {}, private val onDeleteListener: (id: String) -> Unit = {}
) : RecyclerView.Adapter<PreventionAdapter.ViewHolder>() {
    private var expandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PreventionAdapter.ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        val prevention = preventions[position]
        holder.bind(prevention, isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton.setOnClickListener {
            onEditListener(prevention)
        }
    }

    override fun getItemCount() = preventions.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(prevention: Prevention, isExpanded: Boolean, admin: Boolean) {
            itemView.titledropdown.text = prevention.title
            itemView.textdropdown.text = prevention.text
            itemView.imageDropDown.load(prevention.image)
            if (!admin) {
                itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
            }
            itemView.editButton.isVisible = admin
            itemView.deleteButton.isVisible = admin
            itemView.arrowDropDown.isVisible = !admin
            itemView.imageDropDown.isVisible = !admin && isExpanded
            itemView.textdropdown.isVisible = !admin && isExpanded
            itemView.datedropdown.isVisible = !admin && isExpanded
            itemView.sourcedropdown.isVisible = !admin && isExpanded
        }
    }
}