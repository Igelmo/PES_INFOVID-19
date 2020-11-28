package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.MainActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    lateinit var nombre:TextView
    lateinit var recicleview:RecyclerView
    lateinit var textmensaje:EditText
    lateinit var botoneviar:Button

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




    }

}