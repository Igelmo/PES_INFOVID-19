package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.RiskPreventionFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.RiskPreventionRepository

private const val PREVENCIO_NAME = "prevencio"

class PrevencioViewModel : ViewModel() {
    private val repository: RiskPreventionRepository = RiskPreventionFirebaseRepository()
    val preventionLiveData: LiveData<List<RiskPrevention>> = repository.getRiskPrevention()
}


