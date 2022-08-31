package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.DataSource

class RepositoryImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}