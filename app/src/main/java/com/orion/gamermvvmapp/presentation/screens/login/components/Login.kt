package com.orion.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.presentation.components.ProgressBar
import com.orion.gamermvvmapp.presentation.navigation.AppScreen
import com.orion.gamermvvmapp.presentation.screens.login.LoginViewModel

@Composable

fun Login( navController: NavHostController,viewModel: LoginViewModel = hiltViewModel()) {

        when(val loginResponse = viewModel.loginResponse) {
            // Mostrar que se esta realizando la peticion
            Response.Loading -> {
               ProgressBar()
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = AppScreen.Profile.route) {
                        popUpTo(AppScreen.Login.route) {
                            inclusive = true
                        }
                    }
                }
                Toast.makeText(LocalContext.current,"Usuario Logeado", Toast.LENGTH_LONG).show()
            }

            is Response.Failure -> {
                Toast.makeText(LocalContext.current, loginResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else ->{}
        }

}