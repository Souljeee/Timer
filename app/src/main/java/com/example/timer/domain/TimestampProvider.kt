package com.example.timer.domain

interface TimestampProvider {
    fun getMilliseconds(): Long
}