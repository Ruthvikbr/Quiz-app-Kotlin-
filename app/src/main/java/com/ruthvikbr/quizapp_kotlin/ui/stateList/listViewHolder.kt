package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.data.State

class listViewHolder(inflater: LayoutInflater,parent:ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.list_item,parent,false)) {
    private var stateTextView : TextView? = null
    private var capitalTextView : TextView? = null

    init {
        stateTextView = itemView.findViewById(R.id.StateTextView)
        capitalTextView = itemView.findViewById(R.id.capitalTextView)
    }

    fun bind(state: State){
        stateTextView?.text = state.stateName
        capitalTextView?.text = state.capitalName
    }


}