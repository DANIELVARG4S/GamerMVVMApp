package com.orion.gamermvvmapp.presentation.screens.profile_edit


import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.presentation.components.DefaultTopBar
import com.orion.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.orion.gamermvvmapp.presentation.screens.profile_edit.components.Update


@Composable

fun ProfileEditScreen(
    navController: NavHostController,
    user:String
) {
    //Log.d("ProfileEditScreen", "Usuario: $user")
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar Usuario",
                navController = navController
                )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    Update()
}