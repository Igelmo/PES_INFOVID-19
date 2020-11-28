package edu.upc.fib.pes_infovid19.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_create_prevention.*

class CreatePreventionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prevention)
        setSupportActionBar(toolbarCreatePrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        createNewPreventionButton.setOnClickListener {
            onSupportNavigateUp()
        }
    }

    fun constructNewTestType() {

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}