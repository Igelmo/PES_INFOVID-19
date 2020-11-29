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
    fun deletePrevention(id: String) = repository.removePrevention(id)

    fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention) = repository.modifyRiskPrevention(id, riskPrevention)
    fun modifyPrevention(id: String, prevention: Prevention) = repository.modifyPrevention(id, prevention)

    fun addRiskPrevention(riskPrevention: RiskPrevention) = repository.createRiskPrevention(riskPrevention)
    fun addPrevention(prevention: Prevention) = repository.createPrevention(prevention)
}


