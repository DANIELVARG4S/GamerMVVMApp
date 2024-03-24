package com.orion.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.orion.gamermvvmapp.core.Constans.USERS
import com.orion.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.orion.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.orion.gamermvvmapp.domain.repository.AuthRepository
import com.orion.gamermvvmapp.domain.repository.UsersRepository
import com.orion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.orion.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.orion.gamermvvmapp.domain.use_cases.auth.Login
import com.orion.gamermvvmapp.domain.use_cases.auth.Logout
import com.orion.gamermvvmapp.domain.use_cases.auth.Signup
import com.orion.gamermvvmapp.domain.use_cases.users.Create
import com.orion.gamermvvmapp.domain.use_cases.users.GetUserById
import com.orion.gamermvvmapp.domain.use_cases.users.Update
import com.orion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebasefirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser =  GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository),
    )

    @Provides
    fun provideUserUsesCases(repository: UsersRepository) = UsersUseCases (
        create = Create(repository),
        getUserById = GetUserById(repository ),
        update = Update(repository)
    )





}