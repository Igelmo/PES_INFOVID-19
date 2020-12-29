package edu.upc.fib.pes_infovid19.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.TestTypeFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.repository.TestTypeRepository
import edu.upc.fib.pes_infovid19.domain.structures.TestType

private const val TEST_NAME = "typeTests"

class TestTypeViewModel : ViewModel() {

    private val repository: TestTypeRepository = TestTypeFirebaseRepository()
    fun deleteTestType(id: String) = repository.removeTestType(id)
    fun modifyTestType(id: String, testType: TestType) = repository.modifyTestType(id, testType)
    fun addTestType(testType: TestType) = repository.createTestType(testType)

    val testTypeLiveData: LiveData<List<TestType>> = repository.getTestTypes()
}

