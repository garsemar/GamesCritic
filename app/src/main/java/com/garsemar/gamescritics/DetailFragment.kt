package com.garsemar.gamescritics

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.api.Api
import com.garsemar.gamescritics.databinding.FragmentDetailBinding
import com.garsemar.gamescritics.model.game.Game
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("gameId").toString()
        val pos = arguments?.getInt("pos")

        val api = Api()

        Glide.with(this)
            .load(Api.myData[pos!!].background_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(binding.imageGame)
        binding.titleGame.text = Api.myData[pos].name
        binding.rateGame.text = Api.myData[pos].rating.toString()
        binding.dateGame.text = Api.myData[pos].released

        GlobalScope.launch {
            api.getGameApi(id, binding)
            while (Api.myData2.name == "") {
                delay(1000)
            }
            activity?.runOnUiThread {
                binding.descGame.text = Api.myData2.description.replace("<p>", "").replace("</p>", "").replace("<br />", "")
                binding.loading.visibility = View.GONE
                Api.myData2.name = ""
            }
        }


//        parentFragmentManager.setFragmentResultListener("Result", this
//        ) { _: String, results: Bundle ->
//            val result: Int = results.get(("gameId")) as Int
//            binding.titleGame.text = result.toString()
//            api.getGameApi(result.toString(), binding)
//            /*binding.descGame.text = "games.name"
//            binding.dateGame.text = result[pos].released
//            binding.rateGame.text = result[pos].rating.toString()
//            Glide.with(binding.root.context)
//                .load(result[pos].background_image)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .centerCrop()
//                .circleCrop()
//                .into(binding.imageGame)*/
//        }

    }
}