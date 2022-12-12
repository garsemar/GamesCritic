package com.garsemar.gamescritics

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.databinding.FragmentDetailBinding
import java.net.URL


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

        parentFragmentManager.setFragmentResultListener("Games", this
        ) { _: String, result: Bundle ->
            val result2: Game = result.get(("games")) as Game
            binding.titleGame.text = result2.title
            binding.descGame.text = result2.description
            binding.dateGame.text = result2.releaseDate
            binding.rateGame.text = result2.rate.toString()
            Glide.with(binding.root.context)
                .load(result2.img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imageGame)
        }

    }
}