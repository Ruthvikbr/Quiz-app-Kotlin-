package com.ruthvikbr.quizapp_kotlin.ui.quiz

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import com.ruthvikbr.quizapp_kotlin.data.State
import kotlin.random.Random

class QuizView(context: Context) : LinearLayout(context) {

    lateinit var options: RadioGroup

    var correctOptionID: Int? = null

    private lateinit var optionsClickListener: OptionsClickListener

    init {
        initRadios()
    }

    constructor(context: Context, attributeSet: AttributeSet) : this(context) {
        initRadios()
    }


    fun setOnOptionsClickListener(optionsClickListener: OptionsClickListener) {
        this.optionsClickListener = optionsClickListener
    }

    public interface OptionsClickListener {
        fun onClicked(result: Boolean)
    }

    private fun initRadios() {
        options = RadioGroup(context)
        options.id = View.generateViewId()
    }

    public fun setdata(states: List<State>) {
        val random = Random(System.currentTimeMillis())
        val correctOption:Int = random.nextInt(4)

        val correctState = states[correctOption]

        val questionTextView = TextView(context)
        val question = "What is the capital of state ${correctState.stateName} ?"

        questionTextView.text = question
        questionTextView.setPadding(20,20,20,20)
        questionTextView.setTextColor(resources.getColor(android.R.color.black))
        questionTextView.textSize = 24F


        this.addView(questionTextView)
        this.addView(options)









    }
}