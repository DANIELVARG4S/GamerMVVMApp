package com.orion.gamermvvmapp.presentation.screens.login.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.gamermvvmapp.R
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTextField
import com.orion.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.orion.gamermvvmapp.presentation.ui.theme.Blue700
import com.orion.gamermvvmapp.presentation.ui.theme.Dark700

@Composable
fun LoginContent(viewModel: LoginViewModel = hiltViewModel()) {


    val state = viewModel.state
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
                    value = state.email,
                    onValueChange ={ viewModel.onEmailInput(it)},
                    label ="Correo Electrico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrorMsg,
                    validateFiel = { viewModel.validateEmail()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = state.password,
                    onValueChange = {viewModel.onPasswordInput(it)},
                    label = "Contraseña" ,
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrorMsg,
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

}




