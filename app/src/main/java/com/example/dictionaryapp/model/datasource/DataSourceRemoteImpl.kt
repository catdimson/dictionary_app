package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemoteImpl : DataSource<List<DataModel>> {
    private val remoteProvider: RetrofitImpl = RetrofitImpl()

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}