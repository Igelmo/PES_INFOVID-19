package edu.upc.fib.pes_infovid19.domain.repository

import androidx.lifecycle.LiveData
import edu.upc.fib.pes_infovid19.domain.structures.Myth

interface MythsRepository {
    fun getMyths(): LiveData<List<Myth>>
    fun removeMyth(id: String)
    fun modifyMyth(id: String, myth: Myth)
    fun createMyth(myth: Myth)
}