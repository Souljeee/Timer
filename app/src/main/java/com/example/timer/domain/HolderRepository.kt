package com.example.timer.domain

interface HolderRepository {
    fun getTimes() : String
    fun startHolder()
    fun stopHolder()
    fun pauseHolder()
}