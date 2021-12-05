package com.example.timer.domain

import com.example.timer.ui.TimestampMillisecondsFormatter

class HolderRepositoryImpl(
    private val timestampProvider: TimestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    },
    private val stopwatchStateHolder: StopwatchStateHolder = StopwatchStateHolder(
        StopwatchStateCalculator(
            timestampProvider,
            ElapsedTimeCalculator(timestampProvider)
        ),
        ElapsedTimeCalculator(timestampProvider),
        TimestampMillisecondsFormatter()
    )
) : HolderRepository {
    override fun getTimes(): String {
        return stopwatchStateHolder.getStringTimeRepresentation()
    }

    override fun startHolder() {
        stopwatchStateHolder.start()
    }

    override fun stopHolder() {
        stopwatchStateHolder.stop()
    }

    override fun pauseHolder() {
        stopwatchStateHolder.pause()
    }
}