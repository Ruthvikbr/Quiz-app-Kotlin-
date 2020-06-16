package com.ruthvikbr.quizapp_kotlin.ui.stateList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.database.StateRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private var stateRepository: StateRepository = StateRepository.getRepository(application)!!
    var stateList: LiveData<PagedList<State>>
    
     private var sortOrder:MutableLiveData<String> = MutableLiveData()

    init {
        sortOrder.value = "StateID"
        stateList = Transformations.switchMap(sortOrder) { input: String -> stateRepository.getStatesInSortedOrder(input) }
    }



    fun insertState(state: State) = stateRepository.insertState(state)

    fun deleteState(state: State) = stateRepository.deleteState(state)

    fun changeSortOrder(sort:String){
        sortOrder.postValue(sort)
    }

}