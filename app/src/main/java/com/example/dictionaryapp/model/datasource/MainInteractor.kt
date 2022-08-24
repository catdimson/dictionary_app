package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.DataModel
import com.example.dictionaryapp.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) private val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}