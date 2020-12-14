package edu.upc.fib.pes_infovid19.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPreventionFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.repository.RiskPreventionRepository
import edu.upc.fib.pes_infovid19.domain.structures.Prevention
import edu.upc.fib.pes_infovid19.domain.structures.RiskPrevention

private const val PREVENTION_NAME = "prevencio"

class RiskPreventionViewModel : ViewModel() {
    private val repository: RiskPreventionRepository = RiskPreventionFirebaseRepository()
    val preventionLiveData: LiveData<List<RiskPrevention>> = repository.getRiskPrevention()

    fun deleteRiskPrevention(id: String) = repository.removeRiskPrevention(id)
    fun deletePrevention(idRiskPrevention: String, id: String) = repository.removePrevention(idRiskPrevention, id)

    fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention, listCreatedPrevention: List<Prevention>) = repository.modifyRiskPrevention(id, riskPrevention, listCreatedPrevention)
    fun modifyPrevention(idRiskPrevention: String, id: String, prevention: Prevention) = repository.modifyPrevention(idRiskPrevention, id, prevention)

    fun addRiskPrevention(riskPrevention: RiskPrevention) = repository.createRiskPrevention(riskPrevention)
    fun addPrevention(idRiskPrevention: String, prevention: Prevention) = repository.createPrevention(idRiskPrevention, prevention)
}


