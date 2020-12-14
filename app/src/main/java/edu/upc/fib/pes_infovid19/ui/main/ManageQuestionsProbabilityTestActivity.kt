package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.InfectionProbabilityTestViewModel
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_questions_probability_test.*

class ManageQuestionsProbabilityTestActivity : AppCompatActivity() {
    private val viewModel: InfectionProbabilityTestViewModel by viewModels()
    private val adapter = ManageInfectionProbabilityTestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_questions_probability_test)
        setSupportActionBar(toolbarManageQuestionsProbabilityTest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerViewManageInfectionTest.adapter = adapter

        viewModel.questionsProbabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }

        addQuestion.setOnClickListener {
            adapter.addQuestion(QuestionProbabilityTest(text = textFieldNewProbabilityQuestion.text.toString(), points = textFieldNewProbabilityQuestionPoints.text.toString().toDouble()))
            textFieldNewProbabilityQuestion.setText("")
            textFieldNewProbabilityQuestionPoints.setText("")
        }

        buttonProbabilityTestUpdateChanges.setOnClickListener {
            adapter.questionDeletedList.forEach {
                viewModel.deleteQuestionInfectionProbabilityTest(it)
            }
            adapter.questionList.filter { it.id.isNotBlank() }.forEach {
                viewModel.modifyQuestionInfectionProbabilityTest(it.id, it)
            }
            adapter.questionList.filter { it.id.isBlank() }.forEach {
                viewModel.addQuestionInfectionProbabilityTest(it)
            }
            onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
