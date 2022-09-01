package com.example.dictionaryapp.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityHistoryBinding
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.HistoryInteractor
import com.example.dictionaryapp.ui.recyclerview.history.HistoryAdapter
import com.example.dictionaryapp.viewmodel.history.HistoryViewModel
import com.example.utils.viewById
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named


class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val scope by lazy { getKoin().getOrCreateScope("historyScope", named("historyScope")) }
    private val recyclerView by viewById<RecyclerView>(R.id.history_activity_recyclerview)
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
        if (recyclerView.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel = scope.get<HistoryViewModel>()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity) { renderData(it) }
    }

    private fun initViews() {
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}
