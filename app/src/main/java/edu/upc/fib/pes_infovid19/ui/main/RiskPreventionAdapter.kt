package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_inside_textview.view.*

class RiskPreventionAdapter(
    private val isAdmin: Boolean,
    private val onEditListener: (RiskPrevention) -> Unit = {},
    private val onDeleteListener: (id: String) -> Unit = {},
    private val onEditPreventionListener: (idRiskPrevention: String, Prevention) -> Unit = { _, _ -> },
    private val onDeletePreventionListener: (idRiskPrevention: String, id: String) -> Unit = { _, _ -> }
) :
    RecyclerView.Adapter<RiskPreventionAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var adviceList = emptyList<RiskPrevention>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_inside_textview))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        val riskPrevention = adviceList[position]
        holder.bind(riskPrevention, isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton2.setOnClickListener {
            onEditListener(riskPrevention)
        }
        holder.itemView.deleteButton2.setOnClickListener {
            onDeleteListener(riskPrevention.id)
        }
    }

    override fun getItemCount(): Int = adviceList.size

    fun updateRiskPrevention(advice: List<RiskPrevention>) {
        adviceList = advice
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(riskPrevention: RiskPrevention, isExpanded: Boolean, admin: Boolean) {
            itemView.titledropdown2.text = riskPrevention.title
            val recyclerAdapter = PreventionAdapter(
                riskPrevention.recomanacionsAsList, false
            ) {
                onEditPreventionListener(riskPrevention.id, it)
            }
            itemView.recyclerViewDropdown.adapter = recyclerAdapter
            if (!admin) {
                itemView.arrowDropDown2.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
                itemView.recyclerViewDropdown.isVisible = isExpanded
            }
            itemView.editButton2.isVisible = admin
            itemView.deleteButton2.isVisible = admin
            itemView.recyclerViewDropdown.isVisible = !admin && isExpanded
            itemView.arrowDropDown2.isVisible = !admin
        }
    }
}