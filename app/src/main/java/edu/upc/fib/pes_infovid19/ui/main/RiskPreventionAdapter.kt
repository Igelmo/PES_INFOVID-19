package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_inside_textview.view.*
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class RiskPreventionAdapter : RecyclerView.Adapter<RiskPreventionAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var adviceList = emptyList<RiskPrevention>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_inside_textview))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        holder.bind(adviceList[position], isExpanded)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = adviceList.size

    fun updateRiskPrevention(advice: List<RiskPrevention>) {
        adviceList = advice
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(riskPrevention: RiskPrevention, isExpanded: Boolean) {
            itemView.titledropdown2.text = riskPrevention.title
            itemView.arrowDropDown2.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
            itemView.textdropdown.isVisible = isExpanded
        }
    }
}