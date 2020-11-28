package edu.upc.fib.pes_infovid19.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPreventionFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.RiskPreventionRepository

private const val PREVENTION_NAME = "prevencio"

class RiskPreventionViewModel : ViewModel() {
    private val repository: RiskPreventionRepository = RiskPreventionFirebaseRepository()
    val preventionLiveData: LiveData<List<RiskPrevention>> = repository.getRiskPrevention()
}


