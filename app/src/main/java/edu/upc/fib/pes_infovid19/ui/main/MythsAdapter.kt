package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class MythsAdapter(private val isAdmin: Boolean) : RecyclerView.Adapter<MythsAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var mythList = emptyList<Myth>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.drop_down_textview_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        holder.bind(mythList[position], isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton.setOnClickListener {
            val context = it.context
            var title = holder.itemView.titledropdown.text
            var text = holder.itemView.textdropdown.text
            var date = holder.itemView.datedropdown.text
            var source = holder.itemView.sourcedropdown.text
            var id = mythList[position].id
            val intent = Intent(context, EditMythActivity::class.java)
            intent.putExtra("title", title);
            intent.putExtra("text", text);
            intent.putExtra("id", id)
            intent.putExtra("date", date)
            intent.putExtra("source", source)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int = mythList.size

    fun updateMyths(myths: List<Myth>) {
        mythList = myths
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(myth: Myth, isExpanded: Boolean, admin: Boolean) {
            if (!admin) {
                itemView.titledropdown.text = myth.title
                itemView.textdropdown.text = myth.text
                itemView.datedropdown.text = myth.date
                itemView.sourcedropdown.text = myth.source
                itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
                itemView.textdropdown.isVisible = isExpanded
            } else {
                itemView.titledropdown.text = myth.title
                itemView.textdropdown.text = myth.text
                itemView.datedropdown.text = myth.date
                itemView.sourcedropdown.text = myth.source
            }
            itemView.editButton.isVisible = admin
            itemView.deleteButton.isVisible = admin
            itemView.arrowDropDown.isVisible = !admin
            itemView.textdropdown.isVisible = !admin && isExpanded
            itemView.datedropdown.isVisible = !admin && isExpanded
            itemView.sourcedropdown.isVisible = !admin && isExpanded

        }
    }
}