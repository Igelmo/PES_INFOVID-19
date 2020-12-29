package edu.upc.fib.pes_infovid19.domain.repository

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.domain.structures.QuestionProbabilityTest

interface InfectionProbabilityTestRepository {
    fun getQuestionsInfectionProbabilityTest(): LiveData<List<QuestionProbabilityTest>>
    fun removeQuestionInfectionProbabilityTest(id: String)
    fun modifyQuestionInfectionProbabilityTest(id: String, question: QuestionProbabilityTest)
    fun createQuestionInfectionProbabilityTest(question: QuestionProbabilityTest)
}