package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Prevention
import edu.upc.fib.pes_infovid19.ui.main.inflate
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class PreventionAdapter(
    initialPreventions: List<Prevention>, private val isAdmin: Boolean, private val onEditListener: (Prevention) -> Unit = {}
) : RecyclerView.Adapter<PreventionAdapter.ViewHolder>() {
    var createdPreventions = mutableListOf<Prevention>()
    private val _preventions = initialPreventions.toMutableList()
    val preventions: List<Prevention>
        get() = _preventions + createdPreventions

    private var expandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
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
        holder.itemView.deleteButton.setOnClickListener {
            _preventions -= prevention
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = preventions.size

    fun updateCreatedPreventions(listCreatedPreventions: List<Prevention>) {
        createdPreventions = listCreatedPreventions.toMutableList()
        notifyItemRangeChanged(_preventions.size, createdPreventions.size)
    }

    fun updatePrevention(prevention: Prevention) {
        val position = preventions.indexOfFirst { it.id == prevention.id }
        if (position <= _preventions.lastIndex) _preventions[position] = prevention
        else createdPreventions[position] = prevention
        notifyItemChanged(position)
    }

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