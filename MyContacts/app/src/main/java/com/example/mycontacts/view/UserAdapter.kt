package com.example.mycontacts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycontacts.databinding.UserItemBinding
import com.example.mycontacts.model.User

class UserAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var userList = mutableListOf<User>()

    fun setMovies(movies: List<User>?) {
        if (movies != null) {
            this.userList = movies.toMutableList()
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val user = userList[position]
        if (user.imageUrl.isNotEmpty() && user.name.isNotEmpty() && user.id.isNotEmpty()) {
            holder.binding.user = user
            Glide.with(holder.itemView.context).load(user.imageUrl).into(holder.binding.userImage)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

class MainViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

}