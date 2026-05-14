package com.example.antharjalawatch.data.model

data class BorewellEntry(

    val depth: String = "",

    val yield: String = "",

    val year: String = "",

    val area: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val timestamp: Long =
        System.currentTimeMillis()
)