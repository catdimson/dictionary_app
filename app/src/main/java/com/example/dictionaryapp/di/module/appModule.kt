package com.example.dictionaryapp.di.module

import com.example.dictionaryapp.di.DATA_SOURCE_LOCAL
import com.example.dictionaryapp.di.DATA_SOURCE_REMOTE
import com.example.dictionaryapp.di.REPO_LOCAL
import com.example.dictionaryapp.di.REPO_REMOTE
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.*
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single<Interactor<AppState>> {
        MainInteractor(
            remoteRepository = get(named(REPO_REMOTE)),
            localRepository = get(named(REPO_LOCAL))
        )
    }
    single<Repository<List<DataModel>>>(named(REPO_REMOTE)) {
        RepositoryImpl(
            dataSource = get(
                named(
                    DATA_SOURCE_REMOTE
                )
            )
        )
    }
    single<Repository<List<DataModel>>>(named(REPO_LOCAL)) {
        RepositoryImpl(
            dataSource = get(
                named(
                    DATA_SOURCE_LOCAL
                )
            )
        )
    }
    single<DataSource<List<DataModel>>>(named(DATA_SOURCE_REMOTE)) {
        DataSourceRemoteImpl(
            remoteProvider = get()
        )
    }
    single<DataSource<List<DataModel>>>(named(DATA_SOURCE_LOCAL)) {
        DataSourceRoomImpl(
            localProvider = get()
        )
    }
    single { RoomDataBaseImpl() }
    single { RetrofitImpl() }
}

val mainScreen = module {
    viewModel { MainViewModel(interactor = get()) }
}

val historyScreen = module {

}

