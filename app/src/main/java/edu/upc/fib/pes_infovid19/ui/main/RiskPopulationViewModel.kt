package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val RISK_NAME = "riskPopulations"

class RiskPopulationViewModel : ViewModel() {

    private val riskPopulationReference = Firebase.database.reference.child(RISK_NAME)
    private val _riskPopulationLiveData = MutableLiveData<List<RiskPopulation>>().also { data ->
        riskPopulationReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(RiskPopulation::class.java) }.toList()
                else emptyList()
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }
    val riskPopulationLiveData: LiveData<List<RiskPopulation>> = _riskPopulationLiveData
}

