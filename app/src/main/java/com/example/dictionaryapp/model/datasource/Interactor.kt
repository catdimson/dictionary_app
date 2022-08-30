package com.example.dictionaryapp.model.datasource

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T

}