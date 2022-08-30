package com.example.dictionaryapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AlertDialog
import com.example.dictionaryapp.R
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.data.entity.HistoryEntity
import com.example.dictionaryapp.model.data.entity.Meaning

fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(
    appState: AppState,
    isOnline: Boolean
): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {}
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<DataModel> = appState.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(
    dataModel: DataModel, newDataModels:
    ArrayList<DataModel>
) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meaning>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null &&
                !meaning.translation.text.isNullOrBlank()
            ) {
                newMeanings.add(Meaning(meaning.translation, meaning.imageUrl, meaning.transcription))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
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

fun convertMeaningsToString(meanings: List<Meaning>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.text, ", ")
        } else {
            meaning.translation?.text
        }
    }
    return meaningsSeparatedByComma
}

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}

fun getStubAlertDialog(context: Context): AlertDialog {
    return getAlertDialog(context, null, null)
}

fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.dialog_title_stub)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)
    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }
    builder.setCancelable(true)
    builder.setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}