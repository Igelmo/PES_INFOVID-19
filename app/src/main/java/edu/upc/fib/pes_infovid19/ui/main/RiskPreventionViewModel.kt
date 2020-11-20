package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPreventionFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.RiskPreventionRepository

private const val PREVENTION_NAME = "prevencio"

class RiskPreventionViewModel : ViewModel() {
    private val repository: RiskPreventionRepository = RiskPreventionFirebaseRepository()
    val preventionLiveData: LiveData<List<RiskPrevention>> = repository.getRiskPrevention()

    fun deleteRiskPrevention(id: String) = repository.removeRiskPrevention(id)
    fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention) = repository.modifyRiskPrevention(id, riskPrevention)
    fun addRiskPrevention(riskPrevention: RiskPrevention) = repository.createRiskPrevention(riskPrevention)


}


