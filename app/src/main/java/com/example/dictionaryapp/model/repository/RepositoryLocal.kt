package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.AppState

interface RepositoryLocal<T> {

    suspend fun getData(word: String): T

    suspend fun saveToDB(appState: AppState)

}