package com.iflippie.level2_task2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iflippie.level2_task2.model.Questionss
import com.iflippie.level2_task2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    public val questions = arrayListOf<Questionss>()
    private val questionsAdapter =
        questionsAdapter(questions)
    var answer = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questions.add(Questionss(getString(R.string.question_Ok), false))
        questions.add(Questionss(getString(R.string.question_Boomer), true))

        initViews()

    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionsAdapter
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    public fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (ItemTouchHelper.LEFT == direction) {
                    answer = false
                } else if (ItemTouchHelper.RIGHT == direction) {
                    answer = true
                }

                 val position = viewHolder.adapterPosition
                if (questions[position].questionAnswer == answer) {
                    Toast.makeText(this@MainActivity, getString(R.string.toast_Correct), Toast.LENGTH_SHORT ).show()
                    questions.removeAt(position)
                    questionsAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, getString(R.string.toast_Incorrect), Toast.LENGTH_SHORT ).show()
                }
                questionsAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        return ItemTouchHelper(callback)
    }
}
