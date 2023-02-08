package com.garsemar.gamescritics.model.games

data class Result(
    val background_image: String,
    val id: Int,
    val name: String,
    val rating: Double,
    val released: String,
)