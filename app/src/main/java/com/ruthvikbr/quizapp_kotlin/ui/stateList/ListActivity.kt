package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.ui.addState.AddStateActivity


class ListActivity : AppCompatActivity() {

    private lateinit var viewModel:ListViewModel
    private lateinit var deleteState: State

    private val updateActivityCode : Int = 0
    private val addActivityCode : Int = 1

    private val extraDataStateName : String = "state_name"
    private val extraDataCapitalName : String = "capital_name"
    private val extraDataStateId : String = "state_id"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val floatingActionButton:FloatingActionButton= findViewById(R.id.addState)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddStateActivity::class.java)
            startActivityForResult(intent,addActivityCode)
        }

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        val recyclerView:RecyclerView = findViewById(R.id.stateList)

        val listPagingAdapter = ListPagingAdapter()
        recyclerView.adapter = listPagingAdapter

        viewModel.stateList.observe(this, Observer {
            listPagingAdapter.submitList(it)
        })

        val clickListener:ListPagingAdapter.ClickListener = object : ListPagingAdapter.ClickListener{
            override fun onItemClick(view: View, position: Int) {
                val currentState: State? = listPagingAdapter.getStateAtPosition(position)
                if (currentState != null) {
                    launchUpdateActivity(currentState)
                }
            }
        }
        val constraintLayout:ConstraintLayout = findViewById(R.id.constraintLayout)
        val snackBar:Snackbar = Snackbar.make(constraintLayout,"State Deleted",BaseTransientBottomBar.LENGTH_LONG)
            .setAction("UNDO") {
                viewModel.insertState(deleteState)
            }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position:Int = viewHolder.adapterPosition
                deleteState = listPagingAdapter.getStateAtPosition(position)!!
                viewModel.deleteState(deleteState)
                snackBar.show()
            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerView)

        listPagingAdapter.setItemClickListener(clickListener)

    }


    private fun launchUpdateActivity(state: State){
        val intent = Intent(this,AddStateActivity::class.java)
        intent.putExtra(extraDataStateName,state.stateName)
        intent.putExtra(extraDataCapitalName,state.capitalName)
        intent.putExtra(extraDataStateId,state.StateID)
        startActivityForResult(intent,updateActivityCode)

    }
}