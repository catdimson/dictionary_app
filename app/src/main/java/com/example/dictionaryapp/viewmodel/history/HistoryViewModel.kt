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
        val returnAppState = interactor.getData(word, isOnline)
        when (returnAppState) {
            is AppState.Success -> {
                liveData.postValue(parseLocalSearchResults(returnAppState))
            }
            is AppState.Error -> {
                handleError(returnAppState.error)
            }
            else -> {}
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    // в учебных целях сделаем public
    public override fun onCleared() {
        liveData.value = AppState.Success(null)
        super.onCleared()
    }
}