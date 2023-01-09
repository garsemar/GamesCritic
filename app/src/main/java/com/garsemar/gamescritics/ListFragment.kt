package com.garsemar.gamescritics

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garsemar.gamescritics.api.Api
import com.garsemar.gamescritics.databinding.*
import com.garsemar.gamescritics.model.games.Result
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
        api = Api()
        val onClickListener = this
        userAdapter = UserAdapter(getUsers(), onClickListener)
        linearLayoutManager = LinearLayoutManager(context)


        binding.recyclerView.apply {
            setHasFixedSize(true) //Optimitza el rendiment de l’app
            layoutManager = linearLayoutManager
            adapter = userAdapter
            GlobalScope.launch {
                while (api.myData.isEmpty()) {
                    delay(1000)
                }
                activity?.runOnUiThread {
                    adapter = UserAdapter(getUsers(), onClickListener)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getUsers(): List<Result> {
        return api.getApi(binding)
    }

    override fun onClick(gameId: Int) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        action.gameId = gameId
        findNavController().navigate(action)


        /*parentFragmentManager.setFragmentResult(
            "Result", bundleOf("gameId" to gameId)
        )
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, DetailFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
            commit()
        }*/
    }
}

class UserAdapter(private val results: List<Result>, private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context
    var gameId = 1

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)
        fun setListener(gameId: Int){
            binding.root.setOnClickListener {
                listener.onClick(gameId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        with(holder){
            setListener(result.id)
            binding.name.text = result.name
            binding.order.text = result.id.toString()
            Glide.with(context)
                .load(result.background_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.img)
        }
    }
}