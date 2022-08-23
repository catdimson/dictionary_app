package com.example.dictionaryapp.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract fun renderData(appState: T)

    abstract val viewModel: BaseViewModel<T>

}