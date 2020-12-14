package edu.upc.fib.pes_infovid19.domain

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.ui.main.QuestionVulnerabilityTest

interface VulnerableTestRepository {
    fun getQuestionsVulnerabilityTest(): LiveData<List<QuestionVulnerabilityTest>>
    fun removeQuestionVulnerabilityTest(id: String)
    fun modifyQuestionVulnerabilityTest(id: String, question: QuestionVulnerabilityTest)
    fun createQuestionVulnerabilityTest(question: QuestionVulnerabilityTest)
}