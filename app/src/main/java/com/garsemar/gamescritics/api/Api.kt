package com.garsemar.gamescritics.api

import android.util.Log
import android.view.View
import com.garsemar.gamescritics.databinding.FragmentDetailBinding
import com.garsemar.gamescritics.databinding.FragmentListBinding
import com.garsemar.gamescritics.model.game.Game
import com.garsemar.gamescritics.model.games.Result
import com.garsemar.gamescritics.model.games.Games
import retrofit2.*

class Api() {
    private val apiInterface = ApiInterface.create()
    var myData: List<Result> = emptyList()
    lateinit var myData2: Game
    fun getApi(binding: FragmentListBinding): List<Result> {
        val call = apiInterface.getData("https://api.rawg.io/api/games?key=1ec589c6d87d4163a8edd1c1837fcbe1&dates=2022-11-14,2022-12-14")
        call.enqueue(object: Callback<Games> {
            init {
                binding.loading.visibility = View.VISIBLE
            }
            override fun onFailure(call: Call<Games>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }
            override fun onResponse(call: Call<Games?>, response: Response<Games?>) {
                if (response.isSuccessful) {
                    myData = response.body()!!.results
                    binding.loading.visibility = View.GONE
                }
            }
        })
        return myData
    }

    fun getGameApi(id: String, binding: FragmentDetailBinding): Game {
        val call = apiInterface.getGameApi("https://api.rawg.io/api/games/$id?key=1ec589c6d87d4163a8edd1c1837fcbe1")
        call.enqueue(object: Callback<Game> {
            init {
                binding.loading.visibility = View.VISIBLE
            }
            override fun onFailure(call: Call<Game>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }
            override fun onResponse(call: Call<Game?>, response: Response<Game?>) {
                if (response.isSuccessful) {
                    myData2 = response.body()!!
                }
            }
        })
        return myData2
    }
}