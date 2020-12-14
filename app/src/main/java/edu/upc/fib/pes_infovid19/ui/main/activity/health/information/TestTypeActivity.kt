package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.viewmodel.TestTypeViewModel
import edu.upc.fib.pes_infovid19.ui.main.adapter.TestTypeAdapter
import kotlinx.android.synthetic.main.activity_test_type.*

class TestTypeActivity : AppCompatActivity() {
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_type)
        setSupportActionBar(toolbarTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        manageTestTypeButton.setOnClickListener {
            val intent = Intent(this, ManageTestTypeActivity::class.java)
            startActivity(intent)
        }

        val adapter = TestTypeAdapter(false)
        recyclerViewTestType.adapter = adapter

        viewModel.testTypeLiveData.observe(this) { testTypeSnapshot ->
            adapter.updateTestType(testTypeSnapshot)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

