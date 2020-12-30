package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Myth
import edu.upc.fib.pes_infovid19.ui.main.inflate
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class MythsAdapter(private val isAdmin: Boolean, private val onEditListener: (Myth) -> Unit = {}, private val onDeleteListener: (id: String) -> Unit = {}) :
    RecyclerView.Adapter<MythsAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var mythList = emptyList<Myth>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.drop_down_textview_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        val myth = mythList[position]
        holder.bind(myth, isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton.setOnClickListener {
            onEditListener(myth)
        }
        holder.itemView.deleteButton.setOnClickListener {
            onDeleteListener(myth.id)
        }
    }

    override fun getItemCount(): Int = mythList.size

    fun updateMyths(myths: List<Myth>) {
        mythList = myths
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(myth: Myth, isExpanded: Boolean, admin: Boolean) {
            itemView.titledropdown.text = myth.title
            itemView.textdropdown.text = myth.text
            itemView.datedropdown.text = myth.date
            itemView.sourcedropdown.text = myth.source
            if (!admin) {
                itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_up_24 else R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
            itemView.editButton.isVisible = admin
            itemView.deleteButton.isVisible = admin
            itemView.arrowDropDown.isVisible = !admin
            itemView.textdropdown.isVisible = !admin && isExpanded
            itemView.datedropdown.isVisible = !admin && isExpanded
            itemView.sourcedropdown.isVisible = !admin && isExpanded
            itemView.imageDropDown.isVisible = false
            itemView.shareButton.isVisible = !admin && isExpanded
        }
    }
}