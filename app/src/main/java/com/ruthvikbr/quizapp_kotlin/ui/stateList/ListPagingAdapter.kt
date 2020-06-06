package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ruthvikbr.quizapp_kotlin.data.State

class ListPagingAdapter : PagedListAdapter<State, ListViewHolder>(DIFF_CALLBACK){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentState:State? = getItem(position)
        if (currentState != null) {
            holder.bind(currentState)
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<State>(){
            override fun areContentsTheSame(oldItem: State, newItem: State): Boolean  = oldItem.stateName == newItem.stateName

            override fun areItemsTheSame(oldItem: State, newItem: State): Boolean = oldItem == newItem
        }
    }

}