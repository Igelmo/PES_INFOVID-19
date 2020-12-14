package edu.upc.fib.pes_infovid19.domain.repository

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.domain.structures.TestType

interface TestTypeRepository {
    fun getTestTypes(): LiveData<List<TestType>>
    fun removeTestType(id: String)
    fun modifyTestType(id: String, testType: TestType)
    fun createTestType(testType: TestType)
}