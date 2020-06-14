package com.ruthvikbr.quizapp_kotlin.ui.quiz


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.ui.stateList.ListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var view: QuizView
    private lateinit var viewModel: QuizViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        view = findViewById(R.id.quizView)

        viewModel.states.observe(this, Observer {
            if(it.size == 4){
                view.setData(it)
            }
            else{
                Toast.makeText(this,"Add more states",Toast.LENGTH_SHORT).show()
            }
        })

        val optionsClickListener:QuizView.OptionsClickListener = object : QuizView.OptionsClickListener{
            override fun onClicked(result: Boolean) {
                updateResult(result)
            }

        }

        view.setOnOptionsClickListener(optionsClickListener)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.quiz_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.list -> {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateResult( result:Boolean){
        if(result){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show()
        }
        viewModel.refreshGame()
        view.reset()
    }
}
