package com.example.dictionaryapp.viewmodel.history

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.HistoryInteractor
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HistoryViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var interactor: HistoryInteractor

    private lateinit var historyViewModel: HistoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        historyViewModel = HistoryViewModel(interactor)
    }

    @Test
    fun coroutines_TestReturnValueIsNotNull() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = historyViewModel.subscribe()

            Mockito.`when`(interactor.getData("hello", true)).thenReturn(
                AppState.Success(
                    mutableListOf(
                        DataModel("text1", mutableListOf()),
                        DataModel("text2", mutableListOf())
                    )
                )
            )

            try {
                liveData.observeForever(observer)
                historyViewModel.getData("hello", true)
                Assert.assertNotNull(liveData.value)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestCheckRepositoryGetDataMethod() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = historyViewModel.subscribe()
            liveData.observeForever(observer)

            historyViewModel.getData("hello", true)

            verify(interactor, times(1)).getData("hello", true)
        }
    }

    @Test
    fun coroutines_TestReturnCorrectValue() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = historyViewModel.subscribe()

            Mockito.`when`(interactor.getData("hello", true)).thenReturn(
                AppState.Success(
                    mutableListOf(
                        DataModel("text1", mutableListOf()),
                        DataModel("text2", mutableListOf())
                    )
                )
            )

            try {
                liveData.observeForever(observer)
                historyViewModel.getData("hello", true)
                val value: AppState.Success = liveData.value as AppState.Success
                Assert.assertEquals("text1", value.data?.get(0)?.text)
                Assert.assertEquals("text2", value.data?.get(1)?.text)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestOnCleared() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = historyViewModel.subscribe()

            Mockito.`when`(interactor.getData("hello", true)).thenReturn(
                AppState.Success(
                    mutableListOf(
                        DataModel("text3", mutableListOf())
                    )
                )
            )

            try {
                liveData.observeForever(observer)
                historyViewModel.getData("hello", true)
                val value: AppState.Success = liveData.value as AppState.Success
                Assert.assertEquals("text3", value.data?.get(0)?.text)
            } finally {
                liveData.removeObserver(observer)
            }

            historyViewModel.onCleared()
            try {
                liveData.observeForever(observer)
                val value: AppState.Success = liveData.value as AppState.Success
                Assert.assertNull(value.data)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    @Test
    fun coroutines_TestException() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = historyViewModel.subscribe()

            Mockito.`when`(interactor.getData("hello2", false)).thenReturn(
                AppState.Error(
                    Throwable(EXCEPTION_TEXT)
                )
            )

            try {
                liveData.observeForever(observer)
                historyViewModel.getData("hello2", false)

                val value: AppState.Error = liveData.value as AppState.Error
                Assert.assertEquals(value.error.message, EXCEPTION_TEXT)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

    companion object {
        private const val EXCEPTION_TEXT = "Response is null or unsuccessful"
    }
}