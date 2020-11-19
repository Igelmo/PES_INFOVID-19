package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.TestTypeRepository
import edu.upc.fib.pes_infovid19.ui.main.TestType

private const val TEST_NAME = "typeTests"

class TestTypeFirebaseRepository : TestTypeRepository {
    private val database = Firebase.database.reference.child(TEST_NAME)

    private val _testTypeLiveData = MutableLiveData<List<TestType>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(TestType::class.java) }.toList()
                else emptyList()
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }

    override fun getTestTypes(): LiveData<List<TestType>> = _testTypeLiveData

    override fun removeTestType(id: String) {
        TODO("Not yet implemented")
    }

    override fun modifyTestType(id: String, testType: TestType) {
        TODO("Not yet implemented")
    }

    override fun createTestType(testType: TestType) {
        TODO("Not yet implemented")
    }


}