package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.RiskPopulation
import edu.upc.fib.pes_infovid19.ui.main.inflate
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class RiskPopulationAdapter(
    private val isAdmin: Boolean, private val onEditListener: (RiskPopulation) -> Unit = {}, private val onDeleteListener: (id: String) -> Unit = {}
) : RecyclerView.Adapter<RiskPopulationAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var riskPopulationList = emptyList<RiskPopulation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        val riskPopulation = riskPopulationList[position]
        holder.bind(riskPopulation, isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton.setOnClickListener {
            onEditListener(riskPopulation)
        }
        holder.itemView.deleteButton.setOnClickListener {
            onDeleteListener(riskPopulation.id)
        }
        holder.itemView.shareButton.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Ets una persona de risc si davant el Coronavirus si...")
            shareIntent.putExtra(Intent.EXTRA_TEXT, riskPopulation.risk + "\n \n Aplicació Infovid-19")
            shareIntent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, "Aplicació Infovid-19")
            shareIntent.type = "text/plain"
            ContextCompat.startActivity(holder.itemView.context, Intent.createChooser(shareIntent, "Compartir via...:"), null)
        }
    }


    override fun getItemCount(): Int = riskPopulationList.size

    fun updateRiskPopulation(riskPopulation: List<RiskPopulation>) {
        riskPopulationList = riskPopulation
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(riskPopulation: RiskPopulation, isExpanded: Boolean, admin: Boolean) {
            itemView.titledropdown.text = riskPopulation.risk
            itemView.textdropdown.isVisible = false
            itemView.editButton.isVisible = admin
            itemView.deleteButton.isVisible = admin
            itemView.arrowDropDown.isVisible = false
            itemView.textdropdown.isVisible = false
            itemView.datedropdown.isVisible = false
            itemView.sourcedropdown.isVisible = false
            itemView.imageDropDown.isVisible = false
            itemView.shareButton.isVisible = false
            itemView.shareButtonOut.isVisible = true

        }
    }
}