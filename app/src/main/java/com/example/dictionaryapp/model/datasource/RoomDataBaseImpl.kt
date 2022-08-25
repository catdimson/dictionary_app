package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

class RoomDataBaseImpl : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return Observable.empty() //TODO Доделать в следующих уроках
    }
}