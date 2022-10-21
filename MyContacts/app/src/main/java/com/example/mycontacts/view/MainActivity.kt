package com.example.mycontacts.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mycontacts.base.BaseActivity
import com.example.mycontacts.base.MyViewModelFactory
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.di.component.DaggerAppComponent
import com.example.mycontacts.network.ApiService
import com.example.mycontacts.repository.UserRepository
import com.example.mycontacts.viewmodel.UserViewModel
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var service: ApiService

    @Inject
    lateinit var repository: UserRepository

    @Inject
    lateinit var adapter: UserAdapter

    lateinit var viewModel: UserViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DaggerAppComponent.create().inject(this)
        binding.userList.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(repository)
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