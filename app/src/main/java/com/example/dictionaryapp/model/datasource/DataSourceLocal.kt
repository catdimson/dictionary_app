package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.AppState

interface DataSourceLocal<T> {

    suspend fun getData(word: String): T

    suspend fun saveToDB(appState: AppState)

}