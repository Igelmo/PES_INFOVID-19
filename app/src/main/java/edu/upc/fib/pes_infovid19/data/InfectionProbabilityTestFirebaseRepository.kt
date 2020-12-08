package edu.upc.fib.pes_infovid19.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import edu.upc.fib.pes_infovid19.domain.InfectionProbabilityTestRepository
import edu.upc.fib.pes_infovid19.ui.main.QuestionProbabilityTest
import java.util.*

private const val QUESTION_PROBABILITY_TEST_NAME = "PreguntesProbabilitatInfeccio"

class InfectionProbabilityTestFirebaseRepository : InfectionProbabilityTestRepository {
    private val database = Firebase.database.reference.child(QUESTION_PROBABILITY_TEST_NAME)
    private val _questionsProbabilityTestLiveData = MutableLiveData<List<QuestionProbabilityTest>>().also { data ->
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var items = if (snapshot.exists()) snapshot.children.mapNotNull { it.getValue(QuestionProbabilityTest::class.java) }.toList()
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

    override fun getQuestionsInfectionProbabilityTest(): LiveData<List<QuestionProbabilityTest>> = _questionsProbabilityTestLiveData

    override fun removeQuestionInfectionProbabilityTest(id: String) {
        TODO("Not yet implemented")
    }

    override fun modifyQuestionInfectionProbabilityTest(id: String, question: QuestionProbabilityTest) {
        TODO("Not yet implemented")
    }

    override fun createQuestionInfectionProbabilityTest(question: QuestionProbabilityTest) {
        TODO("Not yet implemented")
    }

    private fun setQuestionId(items: List<QuestionProbabilityTest>, ids: List<String>): List<QuestionProbabilityTest> {
        var i = 0
        for (item in items) {
            item.id = ids.get(i)
            i += 1
        }
        return items
    }
}