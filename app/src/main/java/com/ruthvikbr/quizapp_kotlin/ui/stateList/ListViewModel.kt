package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.database.StateRepository

class ListViewModel(application: Application) : AndroidViewModel(application){
    private var stateRepository:StateRepository = StateRepository.getRepository(application)!!
     var stateList:LiveData<PagedList<State>>

    init {
        stateList = stateRepository.getAllStates()
    }

    fun insertState(state: State) = stateRepository.insertState(state)

    fun deleteState(state: State) = stateRepository.deleteState(state)

    fun updateState(state: State) = stateRepository.updateState(state)


}