package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)

}