package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.DataModel
import com.example.dictionaryapp.model.datasource.DataSource
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}