package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.entity.DataModel

class DataSourceRemoteImpl(
    private val remoteProvider: RetrofitImpl
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return remoteProvider.getData(word)
    }
}