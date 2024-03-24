package com.orion.gamermvvmapp.presentation.screens.profile_edit.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Person

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.orion.gamermvvmapp.R
import com.orion.gamermvvmapp.presentation.components.DefaultButton
import com.orion.gamermvvmapp.presentation.components.DefaultTextField
import com.orion.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.orion.gamermvvmapp.presentation.ui.theme.Blue500
import com.orion.gamermvvmapp.presentation.ui.theme.Dark700
import com.orion.gamermvvmapp.presentation.utils.ComposeFileProvider


@Composable

fun ProfileEditContent(navController: NavHostController, viewModel: ProfileEditViewModel = hiltViewModel()) {

    val state = viewModel.state

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->

            uri?.let{viewModel.onGaleyResult(it)}
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { hasimage ->

            viewModel.onCameraResult(hasimage)
        }
    )
    val  context = LocalContext.current
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

                if (viewModel.hasImage && viewModel.imageUri != null) {
                    AsyncImage(
                        modifier = Modifier.height(100.dp).clip(CircleShape),
                        model = viewModel.imageUri,
                        contentDescription = "Selected image"
                    )
                }
                else {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .width(130.dp)
                            .clickable {
                                       val uri = ComposeFileProvider.getImageUri(context)
                                viewModel.imageUri = uri
                                cameraLauncher.launch(uri)
                                //imagePicker.launch("image/*")
                                       },
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Imagen de usuario"
                    )
                }




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
                    text = "Editar",
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
                    onValueChange ={ viewModel.onUserNameInput(it)},
                    label ="Nombre de Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usarnameErrorMsg,
                    validateFiel = {viewModel.validateUsernam()}
                )


                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "ACTUALIZAR DATOS",
                    onClick = { viewModel.onUpdate() },

                )

            }

        }
    }

}