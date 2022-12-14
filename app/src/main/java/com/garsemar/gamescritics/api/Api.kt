package com.garsemar.gamescritics.api

import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.garsemar.gamescritics.databinding.FragmentListBinding
import com.garsemar.gamescritics.model.Result
import com.garsemar.gamescritics.model.Games
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import retrofit2.*
import kotlin.time.seconds

class Api(val binding: FragmentListBinding) {
    private val apiInterface = ApiInterface.create()
    var myData: List<Result> = emptyList()
    fun getApi(): List<Result> {
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
        /*println("------------------------Before------------------------")
        runBlocking {
            while (myData.isEmpty()) {
                delay(2000)
            }
        }
        println("------------------------After------------------------")*/
        return myData
    }
}