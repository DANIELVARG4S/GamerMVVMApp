package com.orion.gamermvvmapp.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import com.orion.gamermvvmapp.presentation.screens.profile.components.ProfileContent


@Composable
fun  ProfileScreen (navController: NavHostController) {
    Scaffold (
       topBar = {},
        content = {
            ProfileContent(navController)
        },
        bottomBar = {}

    )
}