package com.garsemar.gamescritics

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global
import androidx.fragment.app.Fragment
import android.view.*
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.api.Api
import com.garsemar.gamescritics.databinding.*
import com.garsemar.gamescritics.model.Result
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api = Api(binding)
        val onClickListener = this
        userAdapter = UserAdapter(getUsers(), onClickListener)
        linearLayoutManager = LinearLayoutManager(context)


        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = linearLayoutManager
            adapter = userAdapter
            GlobalScope.launch {
                while (api.myData.isEmpty()) {
                    delay(2000)
                }
                activity?.runOnUiThread {
                    adapter = UserAdapter(getUsers(), onClickListener)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getUsers(): List<Result> {
        val games = api.getApi()
        return games
    }

    override fun onClick(games: Result) {
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

class UserAdapter(private val games: List<Result>, private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)
        fun setListener(games: Result){
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
        val Result = games[position]
        with(holder){
            setListener(Result)
            binding.name.text = Result.name
            binding.order.text = Result.id.toString()
            Glide.with(context)
                .load(Result.background_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.img)
        }
    }
}