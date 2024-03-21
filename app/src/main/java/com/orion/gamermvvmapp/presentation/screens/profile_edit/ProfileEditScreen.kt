package com.orion.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.presentation.components.DefaultTopBar
import com.orion.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditContent


@Composable

fun ProfileEditScreen( navController: NavHostController) {
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
}