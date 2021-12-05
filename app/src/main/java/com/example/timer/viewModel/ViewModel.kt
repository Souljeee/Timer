package com.example.timer

import androidx.lifecycle.ViewModel
import com.example.timer.domain.HolderRepository
import com.example.timer.domain.HolderRepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(val holderRepo : HolderRepository = HolderRepositoryImpl()):ViewModel() {

    private val vmScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
    )
    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker


    fun start() {
        if (job == null) startJob()
        holderRepo.startHolder()
    }

    private fun startJob() {
        vmScope.launch {
            while (isActive) {
                mutableTicker.value = holderRepo.getTimes()
                delay(20)
            }
        }
    }

    fun pause() {
        holderRepo.pauseHolder()
        stopJob()
    }

    fun stop() {
        holderRepo.stopHolder()
        clearValue()
        stopJob()
    }

    private fun stopJob() {
        vmScope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = "00:00:000"
    }
}