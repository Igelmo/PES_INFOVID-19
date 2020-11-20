package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_test_type.*

class CreateTestTypeActivity : AppCompatActivity() {
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test_type)
        setSupportActionBar(toolbarCreateTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewTestTypeButton.setOnClickListener {
            onSupportNavigateUp()
        }
    }

    fun constructNewTestType() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}