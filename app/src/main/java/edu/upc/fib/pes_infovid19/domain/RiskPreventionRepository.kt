package edu.upc.fib.pes_infovid19.domain

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.ui.main.Prevention
import edu.upc.fib.pes_infovid19.ui.main.RiskPrevention

interface RiskPreventionRepository {
    fun getRiskPrevention(): LiveData<List<RiskPrevention>>

    fun removeRiskPrevention(id: String)
    fun removePrevention(idRiskPrevention: String, id: String)

    fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention, listCreatedPrevention: List<Prevention>)
    fun modifyPrevention(idRiskPrevention: String, id: String, prevention: Prevention)

    fun createRiskPrevention(riskPrevention: RiskPrevention)
    fun createPrevention(idRiskPrevention: String, prevention: Prevention)
}