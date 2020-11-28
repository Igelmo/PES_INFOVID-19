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
            constructNewTestType()
            onSupportNavigateUp()
        }
    }

    fun constructNewTestType() {
        val testType = TestType()
        testType.name = titleTextTestType.text.toString()
        testType.description = textTestType.text.toString()
        testType.date = dateTestType.text.toString()
        testType.source = sourceTestType.text.toString()
        viewModel.addTestType(testType)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}