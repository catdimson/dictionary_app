package com.example.dictionaryapp.ui

import android.os.Bundle
import com.example.dictionaryapp.databinding.ActivityHistoryBinding
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.HistoryInteractor
import com.example.dictionaryapp.ui.recyclerview.history.HistoryAdapter
import com.example.dictionaryapp.viewmodel.history.HistoryViewModel
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.core.scope.get


class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val scope by lazy { getKoin().getOrCreateScope("historyScope", named("historyScope")) }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel = scope.get<HistoryViewModel>()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
