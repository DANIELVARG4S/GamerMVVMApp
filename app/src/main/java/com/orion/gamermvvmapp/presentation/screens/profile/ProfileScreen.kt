package com.orion.gamermvvmapp.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTopBar
import com.orion.gamermvvmapp.presentation.navigation.AppScreen
import com.orion.gamermvvmapp.presentation.screens.signup.components.SignupContent

@Composable
fun  ProfileScreen (navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Scaffold (
        topBar = {
            DefaultTopBar(
                title = "Nuevo Usuario",
                upAvaliable = true,
                navController = navController
            )
        },
        content = {
            SignupContent(navController)
        },

        bottomBar = {}
    )
}