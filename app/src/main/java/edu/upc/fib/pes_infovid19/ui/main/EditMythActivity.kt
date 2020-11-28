package edu.upc.fib.pes_infovid19.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.activity_edit_myth.*

const val MYTH_EXTRA = "MYTH_EXTRA"

class EditMythActivity : AppCompatActivity() {
    private val viewModel: MythsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_myth)
        setSupportActionBar(toolbarEditMyth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val myth = intent.getSerializableExtra(MYTH_EXTRA) as Myth
        setInfo(myth)
        editMythButton.setOnClickListener {
            saveChanges(myth.id)
            onSupportNavigateUp()
        }
    }

    private fun saveChanges(id: String?) {
        val title = titleTextEditMyth.text.toString()
        val text = textEditMyth.text.toString()
        val date = dateEditMyth.text.toString()
        val source = sourceEditMyth.text.toString()
        val myth = Myth(id as String, title, text, date, source)
        viewModel.modifyMyth(id, myth)
    }

    private fun setInfo(myth: Myth) {
        titleTextEditMyth.setText(myth.title)
        textEditMyth.setText(myth.text)
        dateEditMyth.setText(myth.date)
        sourceEditMyth.setText(myth.source)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}