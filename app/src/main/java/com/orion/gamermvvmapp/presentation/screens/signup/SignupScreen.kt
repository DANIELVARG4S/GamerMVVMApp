package com.orion.gamermvvmapp.presentation.screens.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.orion.gamermvvmapp.presentation.components.DefaultTopBar
import com.orion.gamermvvmapp.presentation.screens.signup.components.SignupContent
import com.orion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme


@Composable
fun SignupScreen(navController: NavHostController) {
    
    Scaffold(
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  PreviewSignupScreen() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background //color de fondo
        ) {
            SignupScreen(rememberNavController())
        }
    }
}