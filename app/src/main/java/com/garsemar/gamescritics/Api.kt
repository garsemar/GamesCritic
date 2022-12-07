package com.garsemar.gamescritics

class Api {
    fun getApi(): List<Game> {
        val games = listOf(
            Game(1, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(1, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(1, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(1, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
        )

        return games
    }
}