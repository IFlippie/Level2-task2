package com.iflippie.level2_task2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_questions.view.*

class questionsAdapter(private val Question: List<Questionss>) : RecyclerView.Adapter<questionsAdapter.ViewHolder>() {

    lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_questions, parent, false)
            )
        }

        /**
         * Returns the size of the list
         */
        override fun getItemCount(): Int {
            return Question.size
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(Question[position])
        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(questions: Questionss) {
            itemView.tvQuestions.text = questions.questionText
            itemView.setOnClickListener{Toast.makeText(
                context,
                "The answer is: " +  questions.questionAnswer ,
                Toast.LENGTH_SHORT
            ).show()}
        }
    }
    }