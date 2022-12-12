package com.garsemar.gamescritics

class Api {
    fun getApi(): List<Game> {
        val games = listOf(
            Game(1, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(2, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(3, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(4, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(5, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(6, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(7, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(8, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(9, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"),
            Game(10, "GTA V", "Lorem", "12-12-2012", 4.5, "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg")
        )

        return games
    }
}