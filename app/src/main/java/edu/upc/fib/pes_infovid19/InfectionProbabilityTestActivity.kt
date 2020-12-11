package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.ui.main.InfectionProbabilityTestAdapter
import edu.upc.fib.pes_infovid19.ui.main.ManageQuestionsProbabilityTestActivity
import edu.upc.fib.pes_infovid19.ui.main.QuestionProbabilityTest
import kotlinx.android.synthetic.main.activity_infection_probability_test.*
import kotlinx.android.synthetic.main.question_test_item.view.*

const val PERCENT_EXTRA = "PERCENT_EXTRA"

class InfectionProbabilityTestActivity : AppCompatActivity() {
    private val viewModel: InfectionProbabilityTestViewModel by viewModels()
    private val adapter = InfectionProbabilityTestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infection_probability_test)
        setSupportActionBar(toolbarInfectionTest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonCheckProbability.setOnClickListener {
            val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
            startActivity(intent)
            generateInfectionResult()
        }

        buttonManageProbabilityQuestions.setOnClickListener {
            val intent = Intent(this, ManageQuestionsProbabilityTestActivity::class.java)
            startActivity(intent)
        }

        recyclerViewInfectionTest.adapter = adapter

        viewModel.questionsProbabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }
    }

    private fun generateInfectionResult() {
        val questionsCheckedList = mutableListOf<QuestionProbabilityTest>()
        val questionsNotCheckedList = mutableListOf<QuestionProbabilityTest>()
        recyclerViewInfectionTest.forEachIndexed { index, view ->
            val question = adapter.questionList[index]
            if (view.question.isChecked) questionsCheckedList.add(question)
            else questionsNotCheckedList.add(question)
        }
        val percent = viewModel.calculateProbabilities(questionsCheckedList, questionsNotCheckedList)
        val intent = Intent(this, ResultInfectionProbabilityTestActivity::class.java)
        intent.putExtra("PERCENT_EXTRA", percent)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}