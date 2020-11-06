package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class TestTypeAdapter : RecyclerView.Adapter<TestTypeAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var typeTestsList = emptyList<TestType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        holder.bind(typeTestsList[position], isExpanded)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
    }


    override fun getItemCount(): Int = typeTestsList.size

    fun updateTestType(testTypeTests: List<TestType>) {
        typeTestsList = testTypeTests
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(type: TestType, isExpanded: Boolean) {
            itemView.titledropdown.text = type.name
            itemView.textdropdown.text = type.description + "\n \n Procediment: " + type.procedure + "\n \n Data: " + type.date + " \n Font: " + type.source
            itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
            itemView.textdropdown.isVisible = isExpanded
        }
    }
}