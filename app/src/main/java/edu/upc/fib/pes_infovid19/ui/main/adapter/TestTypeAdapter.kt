package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.TestType
import edu.upc.fib.pes_infovid19.ui.main.inflate
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class TestTypeAdapter(private val isAdmin: Boolean, private val onEditListener: (TestType) -> Unit = {}, private val onDeleteListener: (id: String) -> Unit = {}) :
    RecyclerView.Adapter<TestTypeAdapter.ViewHolder>() {
    private var expandedPosition = -1
    private var typeTestsList = emptyList<TestType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == expandedPosition
        val testType = typeTestsList[position]
        holder.bind(testType, isExpanded, isAdmin)
        holder.itemView.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
        holder.itemView.editButton.setOnClickListener {
            onEditListener(testType)
        }
        holder.itemView.deleteButton.setOnClickListener {
            onDeleteListener(testType.id)
        }
    }


    override fun getItemCount(): Int = typeTestsList.size

    fun updateTestType(testTypeTests: List<TestType>) {
        typeTestsList = testTypeTests
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(type: TestType, isExpanded: Boolean, admin: Boolean) {
            itemView.titledropdown.text = type.name
            itemView.textdropdown.text = type.description + "\n \n Procediment: " + type.procedure
            itemView.datedropdown.text = type.date
            itemView.sourcedropdown.text = type.source
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