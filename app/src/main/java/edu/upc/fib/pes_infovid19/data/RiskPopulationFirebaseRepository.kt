package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.RiskPopulationRepository
import edu.upc.fib.pes_infovid19.ui.RiskPopulation
import java.util.*

private const val RISK_NAME = "riskPopulations"

class RiskPopulationFirebaseRepository : RiskPopulationRepository {
    private val database = Firebase.database.reference.child(RISK_NAME)

    private val _riskPopulationLiveData = MutableLiveData<List<RiskPopulation>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(RiskPopulation::class.java) }.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for (snap in snapshot.children) {
                    ids.add(snap.key.toString())
                }
                items = setRiskPopulationId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }

    override fun getRiskPopulation(): LiveData<List<RiskPopulation>> = _riskPopulationLiveData

    override fun removeRiskPopulation(id: String) {
        database.child(id).removeValue()
    }

    override fun modifyRiskPopulation(id: String, riskPopulation: RiskPopulation) {
        database.child(id).setValue(riskPopulation)
    }

    override fun createRiskPopulation(riskPopulation: RiskPopulation) {
        database.push().setValue(riskPopulation)
    }

    private fun setRiskPopulationId(items: List<RiskPopulation>, ids: List<String>): List<RiskPopulation> {
        var i = 0
        for (item in items) {
            item.id = ids.get(i)
            i += 1
        }
        return items
    }


}