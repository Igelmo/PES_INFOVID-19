package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_type_tests.*
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class TypeTestsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_tests)
        setSupportActionBar(toolbarTypeTests)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

class TypeTestsAdapter : RecyclerView.Adapter<TypeTestsAdapter.ViewHolder>() {
    private var mExpandedPosition = -1
    private var typeTestsList = emptyList<TypeTests>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isExpanded = position == mExpandedPosition
        viewHolder(holder, position)
        holder.itemView.arrowDropDown.setImageResource(if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_keyboard_arrow_up_24)
        holder.itemView.textdropdown.isVisible = isExpanded
        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1
            else position
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = typeTestsList.size

    fun updateTypeTests(typeTests: List<TypeTests>) {
        typeTestsList = typeTests
        notifyDataSetChanged()
    }

    fun viewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.titledropdown.text = typeTestsList[position].title
        holder.itemView.textdropdown.text = typeTestsList[position].text + "\n \n Data: " + typeTestsList[position].date + " \n Font: " + typeTestsList[position].source
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}