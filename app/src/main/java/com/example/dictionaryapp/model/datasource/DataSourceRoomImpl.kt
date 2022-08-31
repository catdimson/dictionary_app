package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel

class DataSourceRoomImpl(
    private val localProvider: RoomDataBaseImpl
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return localProvider.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        localProvider.saveToDB(appState)
    }
}