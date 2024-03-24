package com.orion.gamermvvmapp.domain.use_cases.users

import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun  invoke(user: User) = repository.update(user)
}