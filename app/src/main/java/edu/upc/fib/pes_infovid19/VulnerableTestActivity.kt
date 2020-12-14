package edu.upc.fib.pes_infovid19

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.lifecycle.observe
import edu.upc.fib.pes_infovid19.ui.main.ManageQuestionsVulnerabilityTestActivity
import edu.upc.fib.pes_infovid19.ui.main.QuestionVulnerabilityTest
import edu.upc.fib.pes_infovid19.ui.main.VulnerableTestAdapter
import edu.upc.fib.pes_infovid19.ui.main.VulnerableTestViewModel
import kotlinx.android.synthetic.main.activity_vulnerable_test.*
import kotlinx.android.synthetic.main.question_test_item.view.*

const val PERCENT_HEALTH_EXTRA = "PERCENT_HEALTH_EXTRA"
const val PERCENT_ECONOMIC_EXTRA = "PERCENT_ECONOMIC_EXTRA"
const val PERCENT_SOCIAL_EXTRA = "PERCENT_SOCIAL_EXTRA"


class VulnerableTestActivity : AppCompatActivity() {
    private val viewModel: VulnerableTestViewModel by viewModels()
    private val adapter = VulnerableTestAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vulnerable_test)
        setSupportActionBar(toolbarVulnerabilityTest)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonCheckVulnerability.setOnClickListener {
            generateVulnerabilityResults()
        }

        buttonManageVulnerabilityTest.setOnClickListener {
            val intent = Intent(this, ManageQuestionsVulnerabilityTestActivity::class.java)
            startActivity(intent)
        }

        recyclerViewVulnerabilityTest.adapter = adapter

        viewModel.questionsVulnerabilityTestLiveData.observe(this) { questionsSnapshot ->
            adapter.updateQuestions(questionsSnapshot)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun generateVulnerabilityResults() {
        val questionsCheckedList = mutableListOf<QuestionVulnerabilityTest>()
        val questionsNotCheckedList = mutableListOf<QuestionVulnerabilityTest>()
        recyclerViewVulnerabilityTest.forEachIndexed { index, view ->
            val question = adapter.questionList[index]
            if (view.question.isChecked) questionsCheckedList.add(question)
            else questionsNotCheckedList.add(question)
        }
        val percentHealth = viewModel.calculateVulnerabilityByType("salut", questionsCheckedList, questionsNotCheckedList)
        val percentEconomic = viewModel.calculateVulnerabilityByType("economica", questionsCheckedList, questionsNotCheckedList)
        val percentSocial = viewModel.calculateVulnerabilityByType("social", questionsCheckedList, questionsNotCheckedList)
        val intent = Intent(this, ResultVulnerableTestActivity::class.java)
        intent.putExtra(PERCENT_HEALTH_EXTRA, percentHealth)
        intent.putExtra(PERCENT_ECONOMIC_EXTRA, percentEconomic)
        intent.putExtra(PERCENT_SOCIAL_EXTRA, percentSocial)
        startActivity(intent)
    }
}