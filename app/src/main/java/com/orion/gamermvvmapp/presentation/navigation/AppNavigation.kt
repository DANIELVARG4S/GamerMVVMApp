package com.orion.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orion.gamermvvmapp.presentation.screens.login.Loginscreen
import com.orion.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.orion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.orion.gamermvvmapp.presentation.screens.signup.SignupScreen


@Composable
fun AppNavigation(navController: NavHostController ) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route // ruta principal LogonScreen
    ){
        composable(route = AppScreen.Login.route) {
            Loginscreen(navController)
        }

        composable(route = AppScreen.Signup.route) {
            SignupScreen(navController)
        }

        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }

        composable(route = AppScreen.ProfileEdit.route) {
            ProfileEditScreen(navController)
        }
    }
}