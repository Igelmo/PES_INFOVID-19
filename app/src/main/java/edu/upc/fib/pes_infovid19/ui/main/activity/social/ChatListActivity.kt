package edu.upc.fib.pes_infovid19.ui.main.activity.social

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import edu.upc.fib.pes_infovid19.ui.main.adapter.ListaXatAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.tarjeta_xat.view.*

class ChatListActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var adapter: ListaXatAdapter
    lateinit var nom: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        toolbar_activity_chat.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        rv = findViewById<RecyclerView>(R.id.rvXats)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val llm = LinearLayoutManager(this)
        adapter = ListaXatAdapter(this)
        rv.layoutManager = llm
        rv.adapter = adapter

        val user = FirebaseAuth.getInstance().currentUser
        val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("email").equalTo(user?.email)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val nom = snapshot.child("username").getValue(String::class.java)!!
                    fillUser(nom)
                    listXats(nom)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }

        })


    }

    fun listXats(user: String) {
        val mDatabase = FirebaseDatabase.getInstance().reference.child("xatinfovid19").child(user)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val name = snapshot.key
                    //val name = snapshot.child("username").getValue(String::class.java)!!
                    if (name != null) {
                        actualitza(name)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }
        })
    }

    fun fillUser(user: String) {
        this.nom = user
    }

    fun actualitza(name: String) {
        if (nom != name) adapter.addPerson(name)

    }

    fun anarXat(v: View) {
        val xat = v.userNameXat.text.toString()
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("xat", xat)
        intent.putExtra("nombre", nom)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun borrarXat(v: View) {
        val xat = v.buttonDeleteXat.hint.toString()
        val mDatabase = FirebaseDatabase.getInstance().reference.child("xatinfovid19").child(nom).child(xat)
        mDatabase.removeValue()
        val intent = Intent(this, ChatListActivity::class.java)
        startActivity(intent)
    }

    fun buscaPersona(v: View) {
        val persona = findViewById<EditText>(R.id.txtCerca).text.toString()
        val mDatabase = FirebaseDatabase.getInstance().reference.child("User").orderByChild("username").equalTo(persona)
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.children.count() > 0) {
                    for (snapshot in dataSnapshot.children) {
                        val nom = snapshot.child("username").getValue(String::class.java)!!
                        bPer(nom)
                    }
                } else error("El usuari es incorrecte o no existeix")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }

        })


    }


    fun bPer(persona: String) {
        if (persona != nom) {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("xat", persona)
            intent.putExtra("nombre", nom)
            startActivity(intent)
        } else {
            error("No pots buscar-te a tú mateix")
        }

    }

    fun error(err: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(err)
        builder.setPositiveButton("Acceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}