package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.RiskPopulationRepository
import edu.upc.fib.pes_infovid19.ui.main.RiskPopulation

private const val RISK_NAME = "riskPopulations"

class RiskPopulationFirebaseRepository : RiskPopulationRepository {
    private val database = Firebase.database.reference.child(RISK_NAME)

    private val _riskPopulationLiveData = MutableLiveData<List<RiskPopulation>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(RiskPopulation::class.java) }.toList()
                else emptyList()
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }

    override fun getRiskPopulation(): LiveData<List<RiskPopulation>> = _riskPopulationLiveData

    override fun removeRiskPopulation(id: String) {
        TODO("Not yet implemented")
    }

    override fun modifyRiskPopulation(id: String, riskPopulation: RiskPopulation) {
        TODO("Not yet implemented")
    }

    override fun createRiskPopulation(riskPopulation: RiskPopulation) {
        TODO("Not yet implemented")
    }


}