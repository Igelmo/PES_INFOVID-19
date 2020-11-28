package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import edu.upc.fib.pes_infovid19.MainActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {
    lateinit var nombre: TextView
    lateinit var recicleview: RecyclerView
    lateinit var textmensaje: EditText
    lateinit var botoneviar: Button
    private lateinit var adapter: Adapter_Mensaje
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        toolbar_activity_chat.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        nombre = findViewById<TextView>(R.id.nombre)
        recicleview = findViewById<RecyclerView>(R.id.rvMensajes)
        textmensaje = findViewById<EditText>(R.id.txtMensaje)
        botoneviar = findViewById<Button>(R.id.btnEnviar)
        adapter = Adapter_Mensaje(this)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("xatinfovid19") ///// SE HA DE CAMBIAR


        var l: LinearLayoutManager = LinearLayoutManager(this)
        recicleview.layoutManager = l

        recicleview.adapter = adapter
        botoneviar.setOnClickListener {
            onClick()
        }
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val m: Mensaje? = dataSnapshot.getValue(Mensaje::class.java)
                if (m != null) {
                    adapter.Add_mensajes(m)
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        ScrollToTopDataObserver(l, recicleview, adapter)
    }

    class ScrollToTopDataObserver(val layoutManager: LinearLayoutManager, val recyclerView: RecyclerView, val adapter: Adapter_Mensaje) : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            setScrollbar()
            super.onItemRangeInserted(positionStart, itemCount)
            val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

            // If the recycler view is initially being loaded or the user is at the bottom of the
            // list, scroll to the bottom of the list to show the newly added message.
            if (lastVisiblePosition == -1 || positionStart >= itemCount - 1 && lastVisiblePosition == positionStart - 1) {
                recyclerView.scrollToPosition(positionStart)
            }
        }

        private fun setScrollbar() {
            recyclerView.scrollToPosition(adapter.getItemCount() - 1)
        }
    }

    fun onClick() {
        //var sdf = SimpleDateFormat("hh:mm")
        //var localdate = LocalDate

        //databaseReference.push().setValue(Mensaje(nombre.text.toString(), textmensaje.text.toString(), sdf.format(localdate)))
        textmensaje.setText("")
    }
}

