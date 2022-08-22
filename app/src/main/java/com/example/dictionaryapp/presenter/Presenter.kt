package com.example.dictionaryapp.presenter

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.ui.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)

}