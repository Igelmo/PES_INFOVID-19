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
        var t = titleTextEditMyth.text.toString()
        var txt = textEditMyth.text.toString()
        var d = dateEditMyth.text.toString()
        var f = sourceEditMyth.text.toString()
        println("title modified:" + t)
        val myth = Myth(t, txt,d,f)
        database.child("myths").child("myth1").child("title").setValue(t)
        database.child("myths").child("myth1").child("text").setValue(txt)
        database.child("myths").child("myth1").child("date").setValue(d)
        database.child("myths").child("myth1").child("source").setValue(f)

    }

    fun getInfo(): List<String?> {
        var title = intent.getStringExtra("title")
        var text = intent.getStringExtra("text")
        var id = intent.getStringExtra("id")
        var info = listOf(id,title,text)
        return info
    }

    fun setInfo(infoMyth:List<String?>) {
        titleTextEditMyth.setText(infoMyth.get(1))
        var text = infoMyth.get(2)?.split("Data")?.get(0)
        var date = infoMyth.get(2)?.split("Data:")?.get(1)?.split("Font:")?.get(0)
        var font = infoMyth.get(2)?.split("Font:")?.get(1)
        textEditMyth.setText(text)
        dateEditMyth.setText(date)
        sourceEditMyth.setText(font)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}