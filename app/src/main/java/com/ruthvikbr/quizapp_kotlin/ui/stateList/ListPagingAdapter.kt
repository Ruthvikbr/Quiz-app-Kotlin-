package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ruthvikbr.quizapp_kotlin.data.State

class ListPagingAdapter : PagedListAdapter<State, ListViewHolder>(DIFF_CALLBACK){

    private lateinit var clickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentState:State? = getItem(position)
        if (currentState != null) {
            holder.bind(currentState)
            holder.itemView.setOnClickListener {
                clickListener.onItemClick(it,position)
            }

        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<State>(){
            override fun areContentsTheSame(oldItem: State, newItem: State): Boolean  = oldItem.stateName == newItem.stateName

            override fun areItemsTheSame(oldItem: State, newItem: State): Boolean = oldItem == newItem
        }

    }

    fun setItemClickListener(mClickListener: ClickListener){
        clickListener = mClickListener
    }

    interface ClickListener{
        fun onItemClick(view:View, position: Int)
    }

    fun getStateAtPosition(position: Int): State? {
        return getItem(position)
    }

}