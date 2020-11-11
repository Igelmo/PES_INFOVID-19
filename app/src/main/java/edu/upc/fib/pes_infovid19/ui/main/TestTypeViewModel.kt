package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val TEST_NAME = "typeTests"

class TestTypeViewModel : ViewModel() {

    private val testTypeReference = Firebase.database.reference.child(TEST_NAME)
    private val _testTypeLiveData = MutableLiveData<List<TestType>>().also { data ->
        testTypeReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(TestType::class.java) }.toList()
                else emptyList()
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }
    val testTypeLiveData: LiveData<List<TestType>> = _testTypeLiveData
}

