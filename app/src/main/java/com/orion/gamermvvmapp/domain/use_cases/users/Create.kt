package com.orion.gamermvvmapp.domain.use_cases.users

import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.repository.AuthRepository
import com.orion.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create  @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)

}