package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_myth.*

const val MYTH_EXTRA = "MYTH_EXTRA"

class EditMythActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_myth)
        setSupportActionBar(toolbarEditMyth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        database = Firebase.database.reference
        var infoMyth : List<String?> =  getInfo()
        setInfo(infoMyth)
        editMythButton.setOnClickListener {
            saveChanges(infoMyth.get(0))
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
    }

    fun saveChanges(id:String?){
        val t = titleTextEditMyth.text.toString()
        val txt = textEditMyth.text.toString()
        val d = dateEditMyth.text.toString()
        val f = sourceEditMyth.text.toString()
        val myth = Myth(id as String,t, txt,d,f)
        database.child("myths").child(id).setValue(myth)


    }

    fun getInfo(): List<String?> {
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")
        val date = intent.getStringExtra("date")
        val source = intent.getStringExtra("source")
        val id = intent.getStringExtra("id")
        println("myth id:" + id)
        val info = listOf(id,title,text,date,source)
        return info
    }

    fun setInfo(infoMyth:List<String?>) {
        titleTextEditMyth.setText(infoMyth.get(1))
        textEditMyth.setText(infoMyth.get(2))
        dateEditMyth.setText(infoMyth.get(3))
        sourceEditMyth.setText(infoMyth.get(4))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}