package com.example.mycontacts.repository

import com.example.mycontacts.model.User
import com.example.mycontacts.network.ApiService
import com.example.mycontacts.network.NetworkState
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: ApiService) {

    suspend fun getUsers() : NetworkState<List<User>> {
        val response = service.getUsers()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}