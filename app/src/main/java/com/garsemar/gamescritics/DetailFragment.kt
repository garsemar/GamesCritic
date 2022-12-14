package com.garsemar.gamescritics

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.databinding.FragmentDetailBinding
import com.garsemar.gamescritics.model.Result


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
            val result2: Result = result.get(("games")) as Result
            binding.titleGame.text = result2.name
            binding.descGame.text = "result2.name"
            binding.dateGame.text = result2.released
            binding.rateGame.text = result2.rating.toString()
            Glide.with(binding.root.context)
                .load(result2.background_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imageGame)
        }

    }
}