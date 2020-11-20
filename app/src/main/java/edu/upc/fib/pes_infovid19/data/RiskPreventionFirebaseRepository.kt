package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.RiskPreventionRepository
import edu.upc.fib.pes_infovid19.ui.main.RiskPrevention
import java.util.*

private const val PREVENTION_NAME = "prevencio"

class RiskPreventionFirebaseRepository : RiskPreventionRepository {
    private val database = Firebase.database.reference.child(PREVENTION_NAME)
    private val _preventionLiveData = MutableLiveData<List<RiskPrevention>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(RiskPrevention::class.java) }.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for (snap in snapshot.children) {
                    ids.add(snap.key.toString())
                }
                items = setRiskPreventionId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    override fun getRiskPrevention(): LiveData<List<RiskPrevention>> = _preventionLiveData

    override fun removeRiskPrevention(id: String) {
        database.child(id).removeValue()
    }

    override fun modifyRiskPrevention(id: String, riskPrevention: RiskPrevention) {
        database.child(id).setValue(riskPrevention)
    }

    override fun createRiskPrevention(riskPrevention: RiskPrevention) {
        database.push().setValue(riskPrevention)
    }

    private fun setRiskPreventionId(items: List<RiskPrevention>, ids: List<String>): List<RiskPrevention> {
        var i = 0
        for (item in items) {
            item.id = ids.get(i)
            i += 1
        }
        return items
    }


}