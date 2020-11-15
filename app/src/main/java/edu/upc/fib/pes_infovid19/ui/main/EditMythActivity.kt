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

        editMythButton.setOnClickListener {
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}