package edu.upc.fib.pes_infovid19.ui.main.activity.health.information

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.Prevention
import kotlinx.android.synthetic.main.activity_create_prevention.*
import java.io.Serializable

const val PREVENTION_EXTRA_CREATE = "PREVENTION_EXTRA_CREATE"

class CreatePreventionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_prevention)
        setSupportActionBar(toolbarCreatePrevention)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val listPrevention = intent.getSerializableExtra(PREVENTION_EXTRA_CREATE) as? List<Prevention> ?: emptyList()
        createNewPreventionButton.setOnClickListener {
            listCreatedPreventionsPassBack(listPrevention)
        }
    }

    fun listCreatedPreventionsPassBack(listPrevention: List<Prevention>) {
        val prevention = Prevention()
        prevention.title = titleTextPrevention.text.toString()
        prevention.text = textPrevention.text.toString()
        prevention.image = urlimage.text.toString()
        val newList = listPrevention + prevention

        val intent = Intent()
        intent.putExtra(LISTCREATEDPREVENTIONS_EXTRA, newList as Serializable)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
