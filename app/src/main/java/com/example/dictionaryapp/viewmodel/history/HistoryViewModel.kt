package com.example.dictionaryapp.viewmodel.history

import androidx.lifecycle.LiveData
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.datasource.HistoryInteractor
import com.example.dictionaryapp.util.parseLocalSearchResults
import com.example.dictionaryapp.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : BaseViewModel<AppState>() {
    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.value = AppState.Success(null)
        super.onCleared()
    }
}