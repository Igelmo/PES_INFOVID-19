package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_test_type.*

class TestTypeActivity : AppCompatActivity() {
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_type)
        setSupportActionBar(toolbarTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = TestTypeAdapter()
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

