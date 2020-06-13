package com.ruthvikbr.quizapp_kotlin.ui.quiz

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class QuizViewModelFactory(application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

}