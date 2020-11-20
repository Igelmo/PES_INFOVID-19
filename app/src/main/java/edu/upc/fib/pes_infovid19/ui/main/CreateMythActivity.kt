package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_myth.*
import kotlinx.android.synthetic.main.activity_edit_myth.*
import java.util.ArrayList

class CreateMythActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_myth)
        setSupportActionBar(toolbarCreateMyth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        database = Firebase.database.reference
        createNewMythButton.setOnClickListener {
            val newMyth = getInfoNewMyth()
            saveNewMyth(newMyth)
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
    }
    fun getInfoNewMyth(): Myth {
        val t = titleTextMyth.text.toString()
        val txt = textMyth.text.toString()
        val d = dateMyth.text.toString()
        val f = sourceMyth.text.toString()
        val myth = Myth("",t, txt,d,f)
        return myth
    }
    fun saveNewMyth(newMyth: Myth){
        database.child("myths").push().setValue(newMyth)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}