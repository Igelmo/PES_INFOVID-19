package edu.upc.fib.pes_infovid19.domain

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.ui.TestType

interface TestTypeRepository {
    fun getTestTypes(): LiveData<List<TestType>>
    fun removeTestType(id: String)
    fun modifyTestType(id: String, testType: TestType)
    fun createTestType(testType: TestType)
}