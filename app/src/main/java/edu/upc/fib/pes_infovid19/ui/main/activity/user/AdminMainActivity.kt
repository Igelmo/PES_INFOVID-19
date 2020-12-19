package edu.upc.fib.pes_infovid19.ui.main.activity.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.ui.main.activity.MainActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.information.ManageMythsActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.information.ManageRiskPopulationActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.information.ManageRiskPreventionActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.information.ManageTestTypeActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.ManageQuestionsProbabilityTestActivity
import edu.upc.fib.pes_infovid19.ui.main.activity.health.tests.ManageQuestionsVulnerabilityTestActivity
import kotlinx.android.synthetic.main.activity_admin_main.*

class AdminMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)
        toolbarAdminMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonManageMyths.setOnClickListener {
            val intent = Intent(this, ManageMythsActivity::class.java)
            startActivity(intent)
        }
        buttonManageRiskPopulation.setOnClickListener {
            val intent = Intent(this, ManageRiskPopulationActivity::class.java)
            startActivity(intent)
        }
        buttonManageRiskPrevention.setOnClickListener {
            val intent = Intent(this, ManageRiskPreventionActivity::class.java)
            startActivity(intent)
        }
        buttonManageTestsType.setOnClickListener {
            val intent = Intent(this, ManageTestTypeActivity::class.java)
            startActivity(intent)
        }
        buttonManageInfectionTestQuestions.setOnClickListener {
            val intent = Intent(this, ManageQuestionsProbabilityTestActivity::class.java)
            startActivity(intent)
        }
        buttonManageVulnerableTestQuestions.setOnClickListener {
            val intent = Intent(this, ManageQuestionsVulnerabilityTestActivity::class.java)
            startActivity(intent)
        }
        buttonCreateAdmin.setOnClickListener {
            val intent = Intent(this, CreateNewAdminActivity::class.java)
            startActivity(intent)
        }
    }
}