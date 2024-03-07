package com.orion.gamermvvmapp.domain.use_cases.auth

import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Signup @Inject constructor(private val  repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signUp(user)
}