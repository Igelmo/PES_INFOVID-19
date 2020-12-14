package edu.upc.fib.pes_infovid19

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.InfectionProbabilityTestFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.InfectionProbabilityTestRepository
import edu.upc.fib.pes_infovid19.ui.main.QuestionProbabilityTest
import kotlin.math.absoluteValue

class InfectionProbabilityTestViewModel : ViewModel() {
    private val repository: InfectionProbabilityTestRepository = InfectionProbabilityTestFirebaseRepository()

    fun deleteQuestionInfectionProbabilityTest(id: String) = repository.removeQuestionInfectionProbabilityTest(id)
    fun modifyQuestionInfectionProbabilityTest(id: String, question: QuestionProbabilityTest) = repository.modifyQuestionInfectionProbabilityTest(id, question)
    fun addQuestionInfectionProbabilityTest(question: QuestionProbabilityTest) = repository.createQuestionInfectionProbabilityTest(question)

    val questionsProbabilityTestLiveData: LiveData<List<QuestionProbabilityTest>> = repository.getQuestionsInfectionProbabilityTest()

    fun calculateProbabilities(questionsChecked: List<QuestionProbabilityTest>, questionsNotChecked: List<QuestionProbabilityTest>): Double {
        val totalQuestions = questionsChecked + questionsNotChecked
        val totalQuestionPoints = totalQuestions.fold(0.0) { acumulador, actual -> acumulador + actual.points }
        val negativeQuestionsPoints = questionsNotChecked.filter { it.points < 0.0 }.fold(0.0) { acumulador, actual ->
            acumulador + actual.points.absoluteValue
        }
        val positiveQuestionsPoints = questionsChecked.filter { it.points > 0.0 }.fold(0.0) { acumulador, actual ->
            acumulador + actual.points
        }
        return (positiveQuestionsPoints + negativeQuestionsPoints) / totalQuestionPoints
    }
}

