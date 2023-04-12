package com.garsemar.gamescritics.model.game

data class Game(
    val background_image: String,
    val description: String,
    val id: Int,
    var name: String,
    val rating: Double,
    val released: String,
)