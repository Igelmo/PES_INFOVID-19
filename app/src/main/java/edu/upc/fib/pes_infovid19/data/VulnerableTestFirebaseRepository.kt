package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.repository.VulnerableTestRepository
import edu.upc.fib.pes_infovid19.domain.structures.QuestionVulnerabilityTest
import java.util.*

private const val QUESTION_VULNERABILITY_TEST_NAME = "PreguntesVulnerabilitat"

class VulnerableTestFirebaseRepository : VulnerableTestRepository {
    private val database = Firebase.database.reference.child(QUESTION_VULNERABILITY_TEST_NAME)
    private val _questionsVulnerabilityTestLiveData = MutableLiveData<List<QuestionVulnerabilityTest>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(QuestionVulnerabilityTest::class.java) }.toList()
                else emptyList()
                val ids: MutableList<String> = ArrayList()
                for (snap in snapshot.children) {
                    ids.add(snap.key.toString())
                }
                items = setQuestionId(items, ids)
                data.postValue(items)
            }

            override fun onCancelled(error: DatabaseError) { //NoOp
            }
        })
    }


    override fun getQuestionsVulnerabilityTest(): LiveData<List<QuestionVulnerabilityTest>> = _questionsVulnerabilityTestLiveData

    override fun removeQuestionVulnerabilityTest(id: String) {
        database.child(id).removeValue()
    }

    override fun modifyQuestionVulnerabilityTest(id: String, question: QuestionVulnerabilityTest) {
        database.child(id).setValue(question)
    }

    override fun createQuestionVulnerabilityTest(question: QuestionVulnerabilityTest) {
        database.push().setValue(question)
    }

    private fun setQuestionId(items: List<QuestionVulnerabilityTest>, ids: List<String>): List<QuestionVulnerabilityTest> {
        var i = 0
        for (item in items) {
            item.id = ids.get(i)
            i += 1
        }
        return items
    }
}