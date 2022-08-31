package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)

}