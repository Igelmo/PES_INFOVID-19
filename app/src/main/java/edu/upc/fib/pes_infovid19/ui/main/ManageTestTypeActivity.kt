package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_manage_myths.*
import kotlinx.android.synthetic.main.activity_manage_test_type.*

class ManageTestTypeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_test_type)
        setSupportActionBar(toolbarManageTestType)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fabCreateNewMyth.setOnClickListener {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}