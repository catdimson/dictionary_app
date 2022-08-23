package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.datasource.DataSourceLocalImpl
import com.example.dictionaryapp.model.datasource.DataSourceRemoteImpl
import com.example.dictionaryapp.model.datasource.MainInteractor
import com.example.dictionaryapp.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemoteImpl()),
        RepositoryImpl(DataSourceLocalImpl())
    )
) : BaseViewModel<AppState>() {
    private var appState: AppState? = null

    fun getState(): AppState? = appState

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    liveData.value = AppState.Loading(null)
                }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = state
                liveData.value = state
            }

            override fun onError(e: Throwable) {
                liveData.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}