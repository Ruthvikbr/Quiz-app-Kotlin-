package com.ruthvikbr.quizapp_kotlin.ui.addState

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.data.State

class AddStateActivity : AppCompatActivity() {

    private lateinit var viewModel: AddStateViewModel

    private val extraDataStateName : String = "state_name"
    private val extraDataCapitalName : String = "capital_name"
    private val extraDataStateId : String = "state_id"

    private lateinit var stateName:String
    private lateinit var capitalName:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_state)


        val newStateEditText:EditText = findViewById(R.id.newState)
        val newCapitalEditText:EditText = findViewById(R.id.newCapital)
        val add:Button = findViewById(R.id.addNewState)

        viewModel = ViewModelProvider(this).get(AddStateViewModel::class.java)

        val extras: Bundle? = intent.extras
        if(extras!=null){
             stateName = extras.getString(extraDataStateName," ")
            if (stateName.isNotEmpty()){
                newStateEditText.setText(stateName)
            }
             capitalName = extras.getString(extraDataCapitalName," ")
            if(capitalName.isNotEmpty()){
                newCapitalEditText.setText(capitalName)
            }

            add.setText(R.string.save)
        }


        add.setOnClickListener {
            val newStateName:String = newStateEditText.text.toString()
            val newCapitalName:String = newCapitalEditText.text.toString()

            if(extras!=null && extras.containsKey(extraDataStateId)){
                val stateID:Long = extras.getLong(extraDataStateId, -1)
                val state = State(newStateName,newCapitalName,stateID)
                viewModel.updateState(state)
                finish()
            }
            else{

                if(newStateName.isEmpty() && newCapitalName.isEmpty()){
                    Toast.makeText(this,"Fill values",Toast.LENGTH_SHORT).show()
                }
                else{
                    val state = State(newStateName,newCapitalName)
                    viewModel.insertState(state)
                    finish()
                }
            }

        }
    }
}