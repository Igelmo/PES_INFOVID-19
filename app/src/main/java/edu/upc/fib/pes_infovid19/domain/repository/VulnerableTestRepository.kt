package edu.upc.fib.pes_infovid19.domain.repository

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.domain.structures.QuestionVulnerabilityTest

interface VulnerableTestRepository {
    fun getQuestionsVulnerabilityTest(): LiveData<List<QuestionVulnerabilityTest>>
    fun removeQuestionVulnerabilityTest(id: String)
    fun modifyQuestionVulnerabilityTest(id: String, question: QuestionVulnerabilityTest)
    fun createQuestionVulnerabilityTest(question: QuestionVulnerabilityTest)
}