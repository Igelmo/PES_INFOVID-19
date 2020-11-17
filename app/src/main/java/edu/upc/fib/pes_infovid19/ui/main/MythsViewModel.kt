package edu.upc.fib.pes_infovid19.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

private const val MYTHS_NAME = "myths"

class MythsViewModel : ViewModel() {
    private val mythsReference = Firebase.database.reference.child(MYTHS_NAME)
    private val _mythsLiveData = MutableLiveData<List<Myth>>().also { data ->
        mythsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(Myth::class.java)}.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for(snap in snapshot.children){
                    ids.add(snap.key.toString())
                }
                println("tots els ids:" + ids)
                items = setMythId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }
    val mythsLiveData: LiveData<List<Myth>> = _mythsLiveData
}
fun setMythId(items: List<Myth>, ids: List<String>): List<Myth> {
    var i = 0
    for(item in items ){
        item.id = ids.get(i)
        i += 1
    }
    return items
}


