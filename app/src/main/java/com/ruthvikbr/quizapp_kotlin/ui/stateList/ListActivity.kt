package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.ui.addState.AddStateActivity

class ListActivity : AppCompatActivity() {

     private lateinit var viewModel:ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val floatingActionButton:FloatingActionButton= findViewById(R.id.addState)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddStateActivity::class.java)
            startActivity(intent)
        }
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val recyclerView:RecyclerView = findViewById(R.id.stateList)
        val listPagingAdapter = ListPagingAdapter()
        recyclerView.adapter = listPagingAdapter
        viewModel.stateList.observe(this, Observer {
            listPagingAdapter.submitList(it)
        })


    }
}