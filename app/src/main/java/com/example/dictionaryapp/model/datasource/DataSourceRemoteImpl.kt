package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemoteImpl(
    private val remoteProvider: RetrofitImpl
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}