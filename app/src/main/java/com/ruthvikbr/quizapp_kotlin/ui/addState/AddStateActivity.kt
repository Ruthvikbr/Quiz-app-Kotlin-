package com.ruthvikbr.quizapp_kotlin.ui.addState

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.data.State

private lateinit var viewModel:AddStateViewModel
class AddStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_state)

        viewModel = ViewModelProvider(this).get(AddStateViewModel::class.java)

        val newStateEditText:EditText = findViewById(R.id.newState)
        val newCapitalEditText:EditText = findViewById(R.id.newCapital)

        val add:Button = findViewById(R.id.addNewState)
        add.setOnClickListener {
            val stateName:String = newStateEditText.text.toString()
            val capitalName:String = newCapitalEditText.text.toString()

            if(stateName.isBlank() && capitalName.isEmpty()){
                Toast.makeText(this,"Fill values",Toast.LENGTH_SHORT).show()
            }
            else{
                val state = State(stateName,capitalName)
                viewModel.insertState(state)
                finish()
            }

        }
    }
}