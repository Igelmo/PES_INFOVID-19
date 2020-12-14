package edu.upc.fib.pes_infovid19.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.upc.fib.pes_infovid19.R
import kotlinx.android.synthetic.main.manage_question_test_item.view.*

class ManageInfectionProbabilityTestAdapter : RecyclerView.Adapter<ManageInfectionProbabilityTestAdapter.ViewHolder>() {


    private var _questionList = mutableListOf<Pair<QuestionProbabilityTest, Boolean>>()

    val questionList
        get() = _questionList.map { it.first }

    private var _questionDeletedList = mutableListOf<QuestionProbabilityTest>()

    val questionDeletedList
        get() = _questionDeletedList.filter { it.id.isNotBlank() }.map { it.id }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.manage_question_test_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (question, editing) = _questionList[position]
        holder.bind(question, editing)
        holder.itemView.setOnClickListener {
            notifyItemChanged(position)
        }
        holder.itemView.editButtonQuestionTest.setOnClickListener {
            _questionList[position] = question to true
            notifyItemChanged(position)
        }
        holder.itemView.completeEditionQuestionTest.setOnClickListener {
            _questionList[position] = holder.newQuestion.copy(id = question.id) to false
            notifyItemChanged(position)
        }
        holder.itemView.cancelEditionQuestionTest.setOnClickListener {
            _questionList[position] = question to false
            notifyItemChanged(position)
        }
        holder.itemView.deleteButtonQuestionTest.setOnClickListener {
            _questionList.removeAt(position)
            _questionDeletedList.add(question)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = questionList.size

    fun updateQuestions(questions: List<QuestionProbabilityTest>) {
        _questionList = questions.map { it to false }.toMutableList()
        notifyDataSetChanged()
    }

    fun addQuestion(questionProbabilityTest: QuestionProbabilityTest) {
        _questionList.add(questionProbabilityTest to false)
        notifyItemInserted(_questionList.lastIndex)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newQuestion: QuestionProbabilityTest
            get() = QuestionProbabilityTest(text = itemView.questionEditableText.text.toString(), points = itemView.questionEditablePoints.text.toString().toDouble())

        fun bind(question: QuestionProbabilityTest, editing: Boolean) {
            itemView.questionText.text = question.text
            itemView.questionEditableText.setText(question.text)
            itemView.questionEditablePoints.setText(question.points.toString())

            itemView.questionText.isVisible = !editing
            itemView.editButtonQuestionTest.isVisible = !editing
            itemView.deleteButtonQuestionTest.isVisible = !editing
            itemView.questionEditableText.isVisible = editing
            itemView.questionEditablePoints.isVisible = editing
            itemView.completeEditionQuestionTest.isVisible = editing
            itemView.cancelEditionQuestionTest.isVisible = editing
            itemView.questionEditableSpinner.isVisible = false
        }
    }
}