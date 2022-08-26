package com.example.dictionaryapp.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.DataModel
import com.example.dictionaryapp.ui.recyclerview.RecyclerAdapter
import com.example.dictionaryapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>() {

    override val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private var adapter: RecyclerAdapter? = null
    private val observer = Observer<AppState> { renderData(it) }
    private val onListItemClickListener: RecyclerAdapter.OnListItemClickListener =
        object : RecyclerAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getState()?.let {
            renderData(it)
        }

        binding.searchFab.setOnClickListener {
            val dialogFragment = DialogFragment.newInstance()
            dialogFragment.setOnSearchClickListener(
                object : DialogFragment.OnSearchClickListener {
                    override fun onClick(searchWord: String) {
                        viewModel.getData(searchWord, true).observe(this@MainActivity, observer)
                    }
                }
            )
            dialogFragment.show(supportFragmentManager, "bottomSheetFragment")
        }

        binding.coroutinesFragmentButton.setOnClickListener {
            val coroutinesFragment = CoroutinesFragment.newInstance()
            binding.coroutinesFragmentButton.isVisible = false
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(binding.successLinearLayout.id, coroutinesFragment)
                .commit()
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            RecyclerAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true).observe(this, observer)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}