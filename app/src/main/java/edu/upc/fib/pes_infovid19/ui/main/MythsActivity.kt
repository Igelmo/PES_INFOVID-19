package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_myths.*
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class MythsActivity : AppCompatActivity() {
    val viewModel: MythsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myths)
        setSupportActionBar(toolbarMyths)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = MythsAdapter()
        recyclerViewMyths.adapter = adapter

        viewModel.mythsLiveData.observe(this) { mythSnapshot ->
            adapter.updateMyths(mythSnapshot)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


class MythsAdapter : RecyclerView.Adapter<MythsAdapter.ViewHolder>() {
    private var mExpandedPosition = -1
    private var mythList = emptyList<Myth>()

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

    override fun getItemCount(): Int = mythList.size

    fun updateMyths(myths: List<Myth>) {
        mythList = myths
        notifyDataSetChanged()
    }

    fun viewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.titledropdown.text = mythList[position].title
        holder.itemView.textdropdown.text = mythList[position].text + "\n \n Data: " + mythList[position].date + " \n Font: " + mythList[position].source
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(context).inflate(layout, this, false)



