package edu.upc.fib.pes_infovid19

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.InfectionProbabilityTestFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.InfectionProbabilityTestRepository
import edu.upc.fib.pes_infovid19.ui.main.QuestionProbabilityTest

class InfectionProbabilityTestViewModel : ViewModel() {
    private val repository: InfectionProbabilityTestRepository = InfectionProbabilityTestFirebaseRepository()
    val questionsProbabilityTestLiveData: LiveData<List<QuestionProbabilityTest>> = repository.getQuestionsInfectionProbabilityTest()
}
