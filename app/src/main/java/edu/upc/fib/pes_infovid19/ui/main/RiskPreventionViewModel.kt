package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val PREVENCIO_NAME = "prevencio"

class PrevencioViewModel : ViewModel() {
    private var adviceList = listOf<RiskPrevention>(
        RiskPrevention(
            "aaaaaaaaaa",
            "bbbbbbbbbbbb",
            mapOf<String, Prevention>("cccccccccccc" to Prevention("ssssssssssss", "wwwwwwwwwww", "eeeeeeeeee")),
            "aAgggggg",
            "eeeeeeeee"
        )
    )
    private val prevencioReference = Firebase.database.reference.child(PREVENCIO_NAME)
    private val _prevencioLiveData = MutableLiveData<List<RiskPrevention>>().also { data ->
        prevencioReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(RiskPrevention::class.java) }.toList()
                else emptyList()
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    val prevencioLiveData: LiveData<List<RiskPrevention>> = liveData { emit(adviceList) }
}


