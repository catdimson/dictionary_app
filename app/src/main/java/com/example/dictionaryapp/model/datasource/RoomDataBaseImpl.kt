package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.data.entity.HistoryEntity
import com.example.dictionaryapp.model.datasource.dao.HistoryDao

class RoomDataBaseImpl(
    private val historyDao: HistoryDao
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    private fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
        return when (appState) {
            is AppState.Success -> {
                val searchResult = appState.data
                if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                    null
                } else {
                    HistoryEntity(searchResult[0].text!!, null)
                }
            }
            else -> null
        }
    }

    private fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
        val dataModel = ArrayList<DataModel>()
        if (list.isNotEmpty()) {
            for (entity in list) {
                dataModel.add(DataModel(entity.word, null))
            }
        }
        return dataModel
    }
}