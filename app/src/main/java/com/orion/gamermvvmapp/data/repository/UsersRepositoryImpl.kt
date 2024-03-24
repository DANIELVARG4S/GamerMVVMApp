package com.orion.gamermvvmapp.data.repository


import com.google.firebase.firestore.CollectionReference
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.HashMap
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val usersRef: CollectionReference): UsersRepository {

    //val firestore = Firebase.firestore
    //val usersRef= firestore.collection("Users")
    override suspend fun create(user: User): Response<Boolean> {

        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return  try {
            val map:MutableMap<String ,Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image

            user.password = ""
            usersRef.document(user.id).update(map).await()
            Response.Success(true)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow{
        val snapShotListener = usersRef.document(id).addSnapshotListener{ snapshot, e ->
            val  user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }

        awaitClose {
            snapShotListener.remove()
        }
    }
}