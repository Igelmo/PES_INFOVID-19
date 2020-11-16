package edu.upc.fib.pes_infovid19.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_myth.*

const val MYTH_EXTRA = "MYTH_EXTRA"

class EditMythActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_myth)
        setSupportActionBar(toolbarEditMyth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var infoMyth : List<String?> =  getInfo()
        println("INFO MYTH: " + infoMyth.get(1))
        setInfo(infoMyth)
        editMythButton.setOnClickListener {
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
    }

    fun getInfo(): List<String?> {
        var title = intent.getStringExtra("title")
        var text = intent.getStringExtra("text")
        var info = listOf(title,text)
        return info
    }

    fun setInfo(infoMyth:List<String?>){
        titleTextEditMyth.setText(infoMyth.get(0))
        var text = infoMyth.get(1)?.split("Data")?.get(0)
        var date = infoMyth.get(1)?.split("Data:")?.get(1)?.split("Font:")?.get(0)
        var font = infoMyth.get(1)?.split("Font:")?.get(1)
        textEditMyth.setText(text)
        dateEditMyth.setText(date)
        sourceEditMyth.setText(font)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}