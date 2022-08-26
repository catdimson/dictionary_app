package com.example.dictionaryapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        stopwatchListener()
        sumNumbersListener()
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

    // 2 Имитация секундомера. Можно сбрасывать.
    private fun stopwatchListener() {
        val scope = CoroutineScope(Dispatchers.IO)
        var jobStopwatch: Job? = null
        binding.stopwatchButton.setOnClickListener {
            binding.stopwatchButtonResult.text = 0.toString()
            jobStopwatch?.cancel()
            jobStopwatch = scope.launch {
                var timer = 0
                while(true) {
                    delay(1_000)
                    withContext(Dispatchers.Main) {
                        timer++
                        binding.stopwatchButtonResult.text = timer.toString()
                    }
                }
            }
        }
        binding.stopStopwatchButton.setOnClickListener {
            scope.launch {
                stopStopwatch(jobStopwatch!!, binding.stopwatchButtonResult)
            }
        }
    }

    private fun stopStopwatch(stopwatchJob: Job, view: TextView) {
        stopwatchJob.cancel()
        view.text = 0.toString()

    }

    // 3 Рассчитать сумму значений через async await
    private fun sumNumbersListener() {
        val scope = CoroutineScope(Dispatchers.IO)
        var jobSumNums: Deferred<Long>? = null
        binding.calculateButton.setOnClickListener {
            binding.calculateResult.text = 0.toString()
            jobSumNums?.cancel()

            jobSumNums = scope.async {
                withContext(Dispatchers.Main) {
                    val num1 = binding.calculateNum1.text.toString().toLong()
                    val num2 = binding.calculateNum2.text.toString().toLong()
                    delay(num1 * 1000 + num2 * 1000)
                    num1 + num2
                }
            }

            scope.launch {
                withContext(Dispatchers.Main) {
                    val sum = jobSumNums?.await().toString()
                    binding.calculateResult.text = sum
                }
            }
        }
    }
}