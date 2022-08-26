package com.example.dictionaryapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dictionaryapp.databinding.FragmentCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesFragment : Fragment() {

    private var _binding: FragmentCoroutinesBinding? = null
    private val binding: FragmentCoroutinesBinding
        get() = _binding!!

    companion object {
        fun newInstance() = CoroutinesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoroutinesBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        await3secListener()
    }

    // 1 По клику ждем 3 сек и появляется сообщение об этом в textview
    private fun await3secListener() {
        val scope = CoroutineScope(Dispatchers.IO)
        var job3sec: Job? = null
        binding.threeSecButton.setOnClickListener {
            binding.threeSecButtonResult.text = null
            job3sec?.cancel()
            job3sec = scope.launch {
                await3Sec()
                withContext(Dispatchers.Main) {
                    binding.threeSecButtonResult.text = "Прошло 3 секунды"
                }
            }
        }
    }

    private suspend fun await3Sec() {
        delay(3_000)
    }
}