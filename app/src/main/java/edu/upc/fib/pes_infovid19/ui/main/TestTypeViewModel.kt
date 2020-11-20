package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.TestTypeFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.TestTypeRepository

private const val TEST_NAME = "typeTests"

class TestTypeViewModel : ViewModel() {

    private val repository: TestTypeRepository = TestTypeFirebaseRepository()

    val testTypeLiveData: LiveData<List<TestType>> = repository.getTestTypes()
}

