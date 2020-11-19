package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.upc.fib.pes_infovid19.data.MythFirebaseRepository
import edu.upc.fib.pes_infovid19.domain.MythsRepository

private const val MYTHS_NAME = "myths"

class MythsViewModel : ViewModel() {
    private val repository: MythsRepository = MythFirebaseRepository()

    fun deleteMyth(id: String) = repository.removeMyth(id)
    val mythsLiveData: LiveData<List<Myth>> = repository.getMyths()
}



