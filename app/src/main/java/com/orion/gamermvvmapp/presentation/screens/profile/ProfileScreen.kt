package com.orion.gamermvvmapp.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.navigation.AppScreen

@Composable
fun  ProfileScreen (navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Scaffold (
        topBar = {},
        content = {
            DefaultButton(
                modifier = Modifier,
                text = "cerrar sesion",
                onClick = {
                    viewModel.logout()
                    navController.navigate(route = AppScreen.Login.route) {
                        popUpTo(AppScreen.Profile.route){ inclusive = true }
                    }
                }
            )
        },
        bottomBar = {}
    )
}