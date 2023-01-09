package com.garsemar.gamescritics

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.garsemar.gamescritics.api.Api
import com.garsemar.gamescritics.databinding.FragmentDetailBinding


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

        val api = Api()

        binding.titleGame.text = arguments?.getInt("gameId").toString()

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