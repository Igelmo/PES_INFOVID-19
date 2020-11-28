package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
        databaseReference = database.getReference("Chat") ///// SE HA DE CAMBIAR


        var l: LinearLayoutManager = LinearLayoutManager(this)
        recicleview.layoutManager = l

        recicleview.adapter = adapter
        botoneviar.setOnClickListener {
            onClick()
        }
        ScrollToTopDataObserver(l, recicleview)

    }

    fun onClick() {
        databaseReference.push().setValue(Mensaje(nombre.text.toString(), textmensaje.text.toString(), "00:00"))
    }
}

class ScrollToTopDataObserver(
    val layoutManager: LinearLayoutManager,
    val recyclerView: RecyclerView
) : RecyclerView.AdapterDataObserver() {
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()

        // If the recycler view is initially being loaded or the user is at the bottom of the
        // list, scroll to the bottom of the list to show the newly added message.
        if (lastVisiblePosition == -1 || positionStart >= itemCount - 1 && lastVisiblePosition == positionStart - 1) {
            recyclerView.scrollToPosition(positionStart)
        }
    }
}