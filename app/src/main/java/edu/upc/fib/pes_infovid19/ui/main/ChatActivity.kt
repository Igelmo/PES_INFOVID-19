package edu.upc.fib.pes_infovid19.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.google.firebase.database.*
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_chat.*
import java.text.SimpleDateFormat
import java.util.*


class ChatActivity : AppCompatActivity() {
    lateinit var nombre: TextView
    lateinit var recicleview: RecyclerView
    lateinit var textmensaje: EditText
    lateinit var botoneviar: Button
    private lateinit var adapter: AdapterMensaje
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        toolbar_activity_chat.setOnClickListener {
            val intent = Intent(this, ChatListActivity::class.java)
            startActivity(intent)
        }
        nombre = findViewById<TextView>(R.id.nombre)
        recicleview = findViewById<RecyclerView>(R.id.rvMensajes)
        textmensaje = findViewById<EditText>(R.id.txtMensaje)
        botoneviar = findViewById<Button>(R.id.btnEnviar)
        adapter = AdapterMensaje(this)
        database = FirebaseDatabase.getInstance()
        val name = intent.extras?.getString("nombre")
        if (name != null) databaseReference = database.getReference("xatinfovid19").child(name)


        val l: LinearLayoutManager = LinearLayoutManager(this)
        recicleview.layoutManager = l

        recicleview.adapter = adapter
        botoneviar.setOnClickListener {
            onClick(l)
        }
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val m: Mensaje? = dataSnapshot.getValue(Mensaje::class.java)
                if (m != null) {
                    adapter.addMessage(m)
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        adapter.registerAdapterDataObserver(object : AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                recicleview.scrollToPosition(adapter.itemCount - 1)
            }
        })



    }


    @SuppressLint("SimpleDateFormat")
    fun onClick(l: LinearLayoutManager) {

        val sdf = SimpleDateFormat("hh:mm")
        val currentDate = sdf.format(Date())
        var m: Mensaje = Mensaje()
        if (textmensaje.text.toString() != "") {
            val userName = intent.extras?.getString("nombre")
            if (userName != null) {
                m.Mensaje(userName, textmensaje.text.toString(), currentDate)
            }
            databaseReference.push().setValue(m)
        }
        textmensaje.setText("")
    }
}

