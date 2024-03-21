package com.orion.gamermvvmapp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.orion.gamermvvmapp.presentation.ui.theme.Blue500
import com.orion.gamermvvmapp.presentation.ui.theme.Blue700


@Composable
fun DefaultTopBar(
    title:String,
    //upAvaliable:Boolean = false,
    navController: NavHostController? = null

) {
    TopAppBar(
        title = {
            Text(text = title,
            fontSize =   19.sp
            )
        },
        backgroundColor = Blue500,
        navigationIcon = {
            IconButton(
                onClick = {navController?.popBackStack()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "" ,
                    tint = Color.White

                )
            }
        }
    ) 
}