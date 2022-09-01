package com.example.dictionaryapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.entity.DataModel
import com.example.dictionaryapp.model.datasource.MainInteractor
import com.example.dictionaryapp.ui.recyclerview.main.MainAdapter
import com.example.dictionaryapp.util.convertMeaningsToString
import com.example.dictionaryapp.viewmodel.main.MainViewModel
import com.example.utils.isOnline
import com.example.utils.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import java.util.concurrent.Executors

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private lateinit var splashScreen: SplashScreen
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val scope by lazy { getKoin().getOrCreateScope("mainScope", named("mainScope")) }
    private val mainActivityRecyclerview by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        stopSplashScreen()

        iniViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun iniViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel = scope.get<MainViewModel>()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        searchFAB.setOnClickListener(fabClickListener)
        mainActivityRecyclerview.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun runSplashScreen() {
        splashScreen = installSplashScreen()
        splashScreen.setKeepVisibleCondition { true }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun stopSplashScreen() {
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(2_000)
            splashScreen.setKeepVisibleCondition { false }
        }
    }
}