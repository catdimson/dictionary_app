package com.example.dictionaryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionaryapp.model.data.AppState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveData: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {
    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveData

    override fun onCleared() {
        compositeDisposable.clear()
    }
}