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
        val infoMyth: List<String?> = getInfo()
        setInfo(infoMyth)
        editMythButton.setOnClickListener {
            saveChanges(infoMyth[0])
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveChanges(id: String?) {
        val t = titleTextEditMyth.text.toString()
        val txt = textEditMyth.text.toString()
        val d = dateEditMyth.text.toString()
        val f = sourceEditMyth.text.toString()
        val myth = Myth(id as String, t, txt, d, f)
        database.child("myths").child(id).setValue(myth)


    }

    private fun getInfo(): List<String?> {
        val title = intent.getStringExtra("title")
        val text = intent.getStringExtra("text")
        val date = intent.getStringExtra("date")
        val source = intent.getStringExtra("source")
        val id = intent.getStringExtra("id")
        return listOf(id, title, text, date, source)
    }

    private fun setInfo(infoMyth: List<String?>) {
        titleTextEditMyth.setText(infoMyth[1])
        textEditMyth.setText(infoMyth[2])
        dateEditMyth.setText(infoMyth[3])
        sourceEditMyth.setText(infoMyth[4])
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}