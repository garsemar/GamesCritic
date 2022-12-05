package com.garsemar.gamescritics

class Api {
    fun getApi(): List<Game> {
        val games = listOf(Game(1, "GTA V", ""))

        return games
    }
}