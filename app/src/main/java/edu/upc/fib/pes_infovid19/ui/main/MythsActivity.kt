package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_myths.*
class MythsActivity : AppCompatActivity() {
    private val viewModel: MythsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myths)
        setSupportActionBar(toolbarMyths)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        manageMythsButton.setOnClickListener {
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }

        val adapter = MythsAdapter(false, { editMyth(it) }) { viewModel.deleteMyth(it) }
        recyclerViewMyths.adapter = adapter

        viewModel.mythsLiveData.observe(this) { mythSnapshot ->
            adapter.updateMyths(mythSnapshot)
        }
    }

    private fun editMyth(myth: Myth) {
        val title = myth.title
        val text = myth.text
        val date = myth.date
        val source = myth.source
        val intent = Intent(this, EditMythActivity::class.java)
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        intent.putExtra("id", myth.id)
        intent.putExtra("date", date)
        intent.putExtra("source", source)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}




