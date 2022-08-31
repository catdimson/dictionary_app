package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.entity.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}