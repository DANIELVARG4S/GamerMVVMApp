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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.gamermvvmapp.R
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTextField
import com.orion.gamermvvmapp.presentation.ui.theme.Blue500
import com.orion.gamermvvmapp.presentation.ui.theme.Dark700
import com.orion.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@Composable
fun SignupContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),

        ) {
        BoxHeader()
        CardForm()
    }
}

@Composable
fun BoxHeader() {
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
            Spacer(modifier = Modifier.height(15.dp))
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
}

@Composable
fun CardForm() {

    var email by remember {
        mutableStateOf( "")
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 150.dp),
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
                text = "Registro",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Por favor ingresa estos datos para continuar",
                fontSize = 12.sp,
                color = Color.Cyan
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = username,
                onValueChange ={ username = it},
                label ="Nombre de Usuario",
                icon = Icons.Default.Person,
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = email,
                onValueChange ={ email = it},
                label ="Correo Electrico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )

            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = confirmPassword,
                onValueChange = {confirmPassword = it},
                label = "Contraseña" ,
                icon = Icons.Default.Lock,
                hideText = true
            )

            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = password,
                onValueChange = {password = it},
                label = "Confirmar Contraseña" ,
                icon = Icons.Outlined.Lock,
                hideText = true
            )


            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                text = "REGISTRARSE",
                onClick = {  }
            )

        }

    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun PreviewSignupContent() {
    GamerMVVMAppTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.background

        ) {
            SignupContent()
        }
    }
}