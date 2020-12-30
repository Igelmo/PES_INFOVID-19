package edu.upc.fib.pes_infovid19.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import edu.upc.fib.pes_infovid19.domain.structures.QuestionVulnerabilityTest
import edu.upc.fib.pes_infovid19.ui.main.inflate
import kotlinx.android.synthetic.main.question_test_item.view.*

class VulnerableTestAdapter : RecyclerView.Adapter<VulnerableTestAdapter.ViewHolder>() {

    private val questionsState = mutableMapOf<QuestionVulnerabilityTest, Boolean>()

    var questionList = emptyList<QuestionVulnerabilityTest>()
        private set

    val checkedQuestions
        get() = questionList.filter { questionsState[it] ?: false }
    val notCheckedQuestions
        get() = questionList.filter { !questionsState.getOrElse(it) { false } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.question_test_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questionList[position]
        holder.bind(question)
        holder.itemView.question.setOnClickListener {
            questionsState[question] = !(questionsState[question] ?: false)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = questionList.size

    fun updateQuestions(questions: List<QuestionVulnerabilityTest>) {
        questionList = questions
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(question: QuestionVulnerabilityTest) {
            itemView.question.text = question.text
            itemView.question.isChecked = questionsState[question] ?: false
        }
    }

}