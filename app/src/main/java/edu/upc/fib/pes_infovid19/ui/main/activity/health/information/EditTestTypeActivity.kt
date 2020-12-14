package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.TestType
import edu.upc.fib.pes_infovid19.domain.viewmodel.TestTypeViewModel
import kotlinx.android.synthetic.main.activity_edit_test_type.*

const val TESTTYPE_EXTRA = "TESTTYPE_EXTRA"


class EditTestTypeActivity : AppCompatActivity() {
    private val viewModel: TestTypeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_test_type)
        setSupportActionBar(toolbarEditTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val testType = intent.getSerializableExtra(TESTTYPE_EXTRA) as TestType
        setInfo(testType)
        editTestTypeButton.setOnClickListener {
            saveChanges(testType.id)
            onSupportNavigateUp()
        }
    }

    private fun saveChanges(id: String?) {
        val name = titleTextEditTestType.text.toString()
        val description = textEditTestType.text.toString()
        val date = dateEditTestType.text.toString()
        val source = sourceEditTestType.text.toString()
        val testType = TestType(id as String, name, description, "", date, source)
        viewModel.modifyTestType(id, testType)
    }

    private fun setInfo(testType: TestType) {
        titleTextEditTestType.setText(testType.name)
        textEditTestType.setText(testType.description + "\n \n Procediment: " + testType.procedure)
        dateEditTestType.setText(testType.date)
        sourceEditTestType.setText(testType.source)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}