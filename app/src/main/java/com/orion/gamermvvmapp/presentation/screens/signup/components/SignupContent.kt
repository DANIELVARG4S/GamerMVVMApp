package com.orion.gamermvvmapp.presentation.screens.signup.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
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

import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.R
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTextField
import com.orion.gamermvvmapp.presentation.navigation.AppScreen
import com.orion.gamermvvmapp.presentation.screens.signup.SignupViewModel
import com.orion.gamermvvmapp.presentation.ui.theme.Blue500
import com.orion.gamermvvmapp.presentation.ui.theme.Dark700


@Composable

fun SignupContent(navController: NavHostController,viewModel: SignupViewModel = hiltViewModel()) {

    val state = viewModel.state


    Box(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Blue500)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(0.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .width(130.dp),
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Imagen de usuario"
                )


            }

        }
        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 120.dp),
            backgroundColor = Dark700
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 30.dp,
                        bottom = 0.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                    text = "Registro",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Cyan
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.username,
                    onValueChange ={ viewModel.onUserNameImput(it)},
                    label ="Nombre de Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usarnameErrorMsg,
                    validateFiel = {viewModel.validateUsername()}
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange ={ viewModel.onEmailImput(it)},
                    label ="Correo Electrico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrorMsg,
                    validateFiel = {viewModel.validateEmail()}

                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = {viewModel.onPasswordInput(it)},
                    label = "Contraseña" ,
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg =  viewModel.passwordErrorMsg,
                    validateFiel = {viewModel.validatepassword()}
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = {viewModel.onConfirmPassword(it)},
                    label = "Confirmar Contraseña" ,
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg =  viewModel.confirmPasswordErrorMsg,
                    validateFiel = {viewModel.validateComfirmPassword()}
                )


                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    text = "REGISTRARSE",
                    onClick = { viewModel.onSignup() },
                    enable = viewModel.isEnableLoginButton
                )

            }

        }
    }

}