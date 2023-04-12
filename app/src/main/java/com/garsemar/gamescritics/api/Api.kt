package com.garsemar.gamescritics.api

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.garsemar.gamescritics.databinding.FragmentDetailBinding
import com.garsemar.gamescritics.databinding.FragmentListBinding
import com.garsemar.gamescritics.model.game.Game
import com.garsemar.gamescritics.model.games.Result
import com.garsemar.gamescritics.model.games.Games
import retrofit2.*
import java.time.LocalDate

class Api() {
    private val apiInterface = ApiInterface.create()
    companion object {
        var myData: List<Result> = emptyList()
        set(myData){
            var j = 1
            field = myData
            for(i in myData.indices){
                field[i].num = j
                j += 1
            }
        }
        var myData2: Game = Game("", "", 0, "", 0.0, "")
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getApi(binding: FragmentListBinding): List<Result> {
        println(LocalDate.now().minusMonths(1))
        if(myData.isEmpty()){
            val call = apiInterface.getData("https://api.rawg.io/api/games?key=1ec589c6d87d4163a8edd1c1837fcbe1&dates=${LocalDate.now().minusMonths(1)},${LocalDate.now()}")
            call.enqueue(object: Callback<Games> {
                init {
                    if (myData.isEmpty()){
                        binding.loading.visibility = View.VISIBLE
                    }
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
        }
        return myData
    }

    fun getGameApi(id: String, binding: FragmentDetailBinding): Game {
        val call = apiInterface.getGameApi("https://api.rawg.io/api/games/$id?key=1ec589c6d87d4163a8edd1c1837fcbe1")
        call.enqueue(object: Callback<Game> {
            init {
                // binding.loading.visibility = View.VISIBLE
            }
            override fun onFailure(call: Call<Game>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }
            override fun onResponse(call: Call<Game?>, response: Response<Game?>) {
                if (response.isSuccessful) {
                    myData2 = response.body()!!
                    println(myData2)
                }
            }
        })
        println(myData2)
        return myData2
    }
}