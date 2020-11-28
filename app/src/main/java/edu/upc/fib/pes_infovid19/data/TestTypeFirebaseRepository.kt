package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.TestTypeRepository
import edu.upc.fib.pes_infovid19.ui.TestType
import java.util.*

private const val TEST_NAME = "typeTests"

class TestTypeFirebaseRepository : TestTypeRepository {
    private val database = Firebase.database.reference.child(TEST_NAME)

    private val _testTypeLiveData = MutableLiveData<List<TestType>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(TestType::class.java) }.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for (snap in snapshot.children) {
                    ids.add(snap.key.toString())
                }
                items = setTestTypeId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }

    override fun getTestTypes(): LiveData<List<TestType>> = _testTypeLiveData

    override fun removeTestType(id: String) {
        database.child(id).removeValue()
    }

    override fun modifyTestType(id: String, testType: TestType) {
        database.child(id).setValue(testType)
    }

    override fun createTestType(testType: TestType) {
        database.push().setValue(testType)
    }
}

private fun setTestTypeId(items: List<TestType>, ids: List<String>): List<TestType> {
    var i = 0
    for (item in items) {
        item.id = ids.get(i)
        i += 1
    }
    return items
}