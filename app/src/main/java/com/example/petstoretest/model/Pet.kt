package com.example.petstoretest.model

data class Pet(
    val id: Long,
    val name: String,
    val status: String // доступные значения: available, pending, sold
)
