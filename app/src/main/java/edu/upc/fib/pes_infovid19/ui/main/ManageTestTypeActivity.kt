package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_test_type.*

class ManageTestTypeActivity : AppCompatActivity() {
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_test_type)
        setSupportActionBar(toolbarManageTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = TestTypeAdapter(true, { }, { })
        recyclerManageViewTestType.adapter = adapter

        viewModel.testTypeLiveData.observe(this) { testTypeSnapshot ->
            adapter.updateTestType(testTypeSnapshot)
        }

        fabCreateNewTestType.setOnClickListener {
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}