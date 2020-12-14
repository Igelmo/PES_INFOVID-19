package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Myth
import edu.upc.fib.pes_infovid19.domain.viewmodel.MythsViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.MythsAdapter
import kotlinx.android.synthetic.main.activity_manage_myths.*

class ManageMythsActivity : AppCompatActivity() {
    private val viewModel: MythsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_myths)
        setSupportActionBar(toolbarManageMyths)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fabCreateNewMyth.setOnClickListener {
            val intent = Intent(this, CreateMythActivity::class.java)
            startActivity(intent)
        }

        val adapter = MythsAdapter(true, { editMyth(it) }, { viewModel.deleteMyth(it) })
        recyclerManageViewMyths.adapter = adapter

        viewModel.mythsLiveData.observe(this) { mythSnapshot ->
            adapter.updateMyths(mythSnapshot)
        }
    }

    private fun editMyth(myth: Myth) {
        val intent = Intent(this, EditMythActivity::class.java)
        intent.putExtra(MYTH_EXTRA, myth)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}