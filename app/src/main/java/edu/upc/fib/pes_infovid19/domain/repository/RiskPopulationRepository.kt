package edu.upc.fib.pes_infovid19.domain.repository

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.domain.structures.RiskPopulation

interface RiskPopulationRepository {
    fun getRiskPopulation(): LiveData<List<RiskPopulation>>
    fun removeRiskPopulation(id: String)
    fun modifyRiskPopulation(id: String, riskPopulation: RiskPopulation)
    fun createRiskPopulation(riskPopulation: RiskPopulation)
}