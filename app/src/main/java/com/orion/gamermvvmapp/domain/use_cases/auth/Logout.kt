package com.orion.gamermvvmapp.domain.use_cases.auth

import com.orion.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private  val repository: AuthRepository){
    operator fun  invoke() = repository.logout()
}