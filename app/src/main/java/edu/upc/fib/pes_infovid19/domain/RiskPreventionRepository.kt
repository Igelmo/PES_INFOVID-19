package edu.upc.fib.pes_infovid19.domain

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.ui.RiskPrevention

interface RiskPreventionRepository {
    fun getRiskPrevention(): LiveData<List<RiskPrevention>>
    fun removeRiskPrevention(id: String)
    fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention)
    fun createRiskPrevention(riskPrevention: RiskPrevention)
}