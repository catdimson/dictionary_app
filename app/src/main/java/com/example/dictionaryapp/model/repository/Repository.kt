package com.example.dictionaryapp.model.repository

interface Repository<T> {

    fun getData(word: String): T

}