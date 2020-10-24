package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_myths.*
import kotlinx.android.synthetic.main.drop_down_textview_item.view.*

class MythsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myths)
        setSupportActionBar(toolbarMyths)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listOfMyths = mutableListOf<Myth>()
        val viewModel = ViewModelProviders.of(this)[MythsViewModel::class.java]
        viewModel.getListMyths().observe(this, Observer { mythSnapshot ->
            listOfMyths.clear()
            listOfMyths.addAll(mythSnapshot)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

class MythsAdapter(private val mythList: MutableList<Myth>) : RecyclerView.Adapter<MythsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.drop_down_textview_item))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.dropdown_text_view.setTitleText(mythList[position].title)
        holder.itemView.dropdown_text_view.setContentText(mythList[position].text)
    }

    override fun getItemCount(): Int = mythList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(context).inflate(layout, this, false)



