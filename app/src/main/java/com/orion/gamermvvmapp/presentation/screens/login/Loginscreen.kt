package com.orion.gamermvvmapp.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.orion.gamermvvmapp.presentation.screens.login.components.Login
import com.orion.gamermvvmapp.presentation.screens.login.components.LoginContent
import com.orion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme


@Composable
fun Loginscreen(navController: NavHostController) {



    Scaffold(
        topBar = {},
        content = {
            LoginContent()
        },
        bottomBar = {
            LoginBottombar(navController)
        }
    )

    //Manejar eatdo de peticion de login
    Login(navController = navController)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background //color de fondo
        ) {
            Loginscreen(rememberNavController())
        }
    }
}