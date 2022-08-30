package com.example.dictionaryapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionaryapp.model.data.entity.HistoryEntity
import com.example.dictionaryapp.model.datasource.dao.HistoryDao

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = true)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

}