package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.repository.MythsRepository
import edu.upc.fib.pes_infovid19.domain.structures.Myth
import java.util.*

private const val MYTHS_NAME = "myths"

class MythFirebaseRepository : MythsRepository {
    private val database = Firebase.database.reference.child(MYTHS_NAME)
    private val _mythsLiveData = MutableLiveData<List<Myth>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(Myth::class.java) }.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for (snap
                in snapshot.children) {
                    ids.add(snap.key.toString())
                }
                items = setMythId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }

    override fun getMyths(): LiveData<List<Myth>> = _mythsLiveData

    override fun removeMyth(id: String) {
        database.child(id).removeValue()
    }

    override fun modifyMyth(id: String, myth: Myth) {
        database.child(id).setValue(myth)
    }

    override fun createMyth(myth: Myth) {
        database.push().setValue(myth)
    }

    private fun setMythId(items: List<Myth>, ids: List<String>): List<Myth> {
        var i = 0
        for (item in items) {
            item.id = ids.get(i)
            i += 1
        }
        return items
    }

}