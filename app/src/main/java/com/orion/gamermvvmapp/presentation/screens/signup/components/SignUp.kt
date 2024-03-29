package com.orion.gamermvvmapp.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.presentation.components.ProgressBar
import com.orion.gamermvvmapp.presentation.navigation.AppScreen
import com.orion.gamermvvmapp.presentation.screens.signup.SignupViewModel

@Composable
fun SignUp(navController:NavHostController,viewModel: SignupViewModel = hiltViewModel()) {
    when(val sinupResponse = viewModel.signUpResponse) {
        Response.Loading -> {
           ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.createUser()
                navController.popBackStack(AppScreen.Login.route, inclusive = true)
                navController.navigate(route = AppScreen.Profile.route)
            }

        }
        is  Response.Failure ->{
            Toast.makeText(LocalContext.current, sinupResponse.exception?.message?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}