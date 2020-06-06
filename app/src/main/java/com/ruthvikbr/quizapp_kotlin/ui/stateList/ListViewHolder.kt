package com.ruthvikbr.quizapp_kotlin.ui.stateList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.ruthvikbr.quizapp_kotlin.R

import com.ruthvikbr.quizapp_kotlin.data.State
import kotlinx.android.synthetic.main.list_item.view.*

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var stateTextView = itemView.StateTextView
    private var capitalTextView = itemView.capitalTextView

    fun bind(state: State) {
        stateTextView?.text = state.stateName
        capitalTextView?.text = state.capitalName
    }

    companion object{
        fun from(parent:ViewGroup):ListViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item,parent,false)
            return ListViewHolder(view)
        }
    }
}