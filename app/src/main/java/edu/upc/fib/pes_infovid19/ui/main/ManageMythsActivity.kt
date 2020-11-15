package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_myths.*

class ManageMythsActivity : AppCompatActivity() {
    private val viewModel: MythsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_myths)
        setSupportActionBar(toolbarManageMyths)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = MythsAdapter(true)
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