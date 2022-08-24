package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocalImpl(
    private val localProvider: RoomDataBaseImpl
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return localProvider.getData(word)
    }
}