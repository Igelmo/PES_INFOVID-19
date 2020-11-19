package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPopulationFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.RiskPopulationRepository

private const val RISK_NAME = "riskPopulations"

class RiskPopulationViewModel : ViewModel() {

    private val repository: RiskPopulationRepository = RiskPopulationFirebaseRepository()
    val riskPopulationLiveData: LiveData<List<RiskPopulation>> = repository.getRiskPopulation()
}

