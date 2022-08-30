package com.example.dictionaryapp.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T

}