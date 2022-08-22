package com.example.dictionaryapp.ui

import com.example.dictionaryapp.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}