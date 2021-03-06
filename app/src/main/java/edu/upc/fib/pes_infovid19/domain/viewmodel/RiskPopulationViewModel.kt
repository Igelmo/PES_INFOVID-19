package edu.upc.fib.pes_infovid19.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPopulationFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.repository.RiskPopulationRepository
import edu.upc.fib.pes_infovid19.domain.structures.RiskPopulation

private const val RISK_NAME = "riskPopulations"

class RiskPopulationViewModel : ViewModel() {

    private val repository: RiskPopulationRepository = RiskPopulationFirebaseRepository()
    val riskPopulationLiveData: LiveData<List<RiskPopulation>> = repository.getRiskPopulation()
    fun deleteRiskPopulation(id: String) = repository.removeRiskPopulation(id)
    fun modifyRiskPopulation(id: String, riskPopulation: RiskPopulation) = repository.modifyRiskPopulation(id, riskPopulation)
    fun addRiskPopulation(riskPopulation: RiskPopulation) = repository.createRiskPopulation(riskPopulation)
}

