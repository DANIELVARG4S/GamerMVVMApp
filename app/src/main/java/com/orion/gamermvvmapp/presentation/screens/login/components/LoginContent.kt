package com.orion.gamermvvmapp.presentation.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.R
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTextField
import com.orion.gamermvvmapp.presentation.navigation.AppScreen
import com.orion.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.orion.gamermvvmapp.presentation.ui.theme.Blue700
import com.orion.gamermvvmapp.presentation.ui.theme.Dark700

@Composable
fun LoginContent(navController: NavHostController,viewModel: LoginViewModel = hiltViewModel()) {

    val loginFlow = viewModel.loginFlow.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        Box(
            modifier = Modifier
                .height(280.dp)
                .background(Blue700)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .width(130.dp),
                    painter = painterResource(id = R.drawable.ic_game_white),
                    contentDescription = "Control"
                )

                Text(

                    text = "FIREBASE MVVM"
                )
            }

        }
        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 180.dp),
            backgroundColor = Dark700
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 40.dp,
                        bottom = 0.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia sesion para continuar",
                    fontSize = 12.sp,
                    color = Color.Cyan
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange ={ viewModel.email.value = it},
                    label ="Correo Electrico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrorMsg.value,
                    validateFiel = { viewModel.validateEmail()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = {viewModel.password.value = it},
                    label = "Contraseña" ,
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrorMsg.value,
                    validateFiel = {
                        viewModel.validatepassword()
                    }
                )


                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    text = "INICIAR SESION",
                    onClick = {

                              viewModel.login()
                        //Log.d("LoginContent", "Email:${viewModel.email.value}")
                        //Log.d("LoginContent","Password: ${viewModel.password.value}")
                    },
                    enable = viewModel.isEnableLoginButton
                )

            }

        }
    }
    loginFlow.value.let {
        when(it) {
            // Mostrar que se esta realizando la peticion
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator()
                }
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
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else ->{}
        }
    }
}




