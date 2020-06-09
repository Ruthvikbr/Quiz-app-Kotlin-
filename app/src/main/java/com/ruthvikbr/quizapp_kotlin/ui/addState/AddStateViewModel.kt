package com.ruthvikbr.quizapp_kotlin.ui.addState

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.database.StateRepository

class AddStateViewModel(application: Application) : AndroidViewModel(application){
    private var stateRepository: StateRepository = StateRepository.getRepository(application)!!

    fun insertState(state: State) = stateRepository.insertState(state)

    fun updateState(state: State) = stateRepository.updateState(state)
}