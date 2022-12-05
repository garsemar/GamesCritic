package com.garsemar.gamescritics

import android.content.ClipDescription
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.databinding.FragmentListBinding
import com.garsemar.gamescritics.databinding.ItemUserBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(val id: Long, var title: String, val description: String, val releaseDate: String, val rate: Double, var img: String): Parcelable

lateinit var api: Api

class ListFragment : Fragment(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(context)

        api = Api()

        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }


    private fun getUsers(): List<Game> {
        val games = api.getApi()
        return games
    }

    override fun onClick(games: Game) {
        parentFragmentManager.setFragmentResult(
            "Games", bundleOf("games" to games)
        )
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, DetailFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }
    }
}

class UserAdapter(private val games: List<Game>, private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)
        fun setListener(games: Game){
            binding.root.setOnClickListener {
                listener.onClick(games)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        with(holder){
            setListener(game)
            binding.name.text = game.title
            binding.order.text = game.id.toString()
            Glide.with(context)
                .load(game.img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.img)
        }
    }
}