package com.garsemar.gamescritics.model

data class Games(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>,
    val user_platforms: Boolean
)