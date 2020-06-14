package com.ruthvikbr.quizapp_kotlin.ui.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.database.StateRepository
import java.util.concurrent.ExecutionException

class QuizViewModel(application: Application) : AndroidViewModel(application) {
     val states: MutableLiveData<List<State>> = MutableLiveData<List<State>>()
    private val stateRepository: StateRepository = StateRepository.getRepository(application)!!

    init {
        loadGame()
    }

    private fun loadGame() {
        try {
            states.postValue(stateRepository.getQuizStates())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

    }

     fun refreshGame(){
        loadGame()
    }

}