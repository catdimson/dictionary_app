package com.example.dictionaryapp.presenter

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.datasource.DataSourceLocalImpl
import com.example.dictionaryapp.model.datasource.DataSourceRemoteImpl
import com.example.dictionaryapp.model.datasource.MainInteractor
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.ui.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemoteImpl()),
        RepositoryImpl(DataSourceLocalImpl())
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    currentView?.renderData(AppState.Loading(null))
                }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}