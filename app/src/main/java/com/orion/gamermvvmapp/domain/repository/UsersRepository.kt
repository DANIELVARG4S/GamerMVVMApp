package com.orion.gamermvvmapp.domain.repository

import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.model.User
import java.io.File
import java.util.concurrent.Flow

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>

    suspend fun update(user: User ): Response<Boolean>

    suspend fun saveImage(file: File): Response<String>

    // flow cambios en tiempo real
    fun getUserById(id:String): kotlinx.coroutines.flow.Flow<User>
}