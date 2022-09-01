package com.example.dictionaryapp.di.module

import androidx.room.Room
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.HistoryInteractor
import com.example.dictionaryapp.model.datasource.MainInteractor
import com.example.dictionaryapp.model.datasource.RetrofitImpl
import com.example.dictionaryapp.model.datasource.RoomDataBaseImpl
import com.example.dictionaryapp.model.db.HistoryDataBase
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.model.repository.RepositoryLocal
import com.example.dictionaryapp.model.repository.RepositoryLocalImpl
import com.example.dictionaryapp.viewmodel.history.HistoryViewModel
import com.example.dictionaryapp.viewmodel.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> {
        RepositoryImpl(dataSource = RetrofitImpl())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryLocalImpl(dataSource = RoomDataBaseImpl(get()))
    }
}

val mainScreen = module {
    scope(named("mainScope")) {
        scoped { MainInteractor(get(), get()) }
        factory { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named("historyScope")) {
        scoped { HistoryInteractor(get(), get()) }
        factory { HistoryViewModel(get()) }
    }
}

