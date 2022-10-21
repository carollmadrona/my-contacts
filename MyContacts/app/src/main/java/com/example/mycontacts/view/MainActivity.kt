package com.example.mycontacts.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.base.BaseActivity
import com.example.mycontacts.base.MyViewModelFactory
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.network.ApiService
import com.example.mycontacts.repository.UserRepository
import com.example.mycontacts.viewmodel.UserViewModel


class MainActivity : BaseActivity() {
    lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = ApiService.getInstance()
        val mainRepository = UserRepository(retrofitService)
        binding.userList.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(UserViewModel::class.java)


        viewModel.movieList.observe(this) {
            adapter.setMovies(it.distinct().toList())
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getUsers()

    }
}