package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.model.datasource.MainInteractor

class MainViewModelFactory(
    private val interactor: MainInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(interactor) as T
    }
}