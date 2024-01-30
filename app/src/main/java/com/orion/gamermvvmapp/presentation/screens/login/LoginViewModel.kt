package com.orion.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    //email
    var email : MutableState<String> = mutableStateOf( "")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password :MutableState<String> = mutableStateOf( "")
    var isPassvalid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    //BUTTON
    var isEnableLoginButton = false

    fun enableLoginButton() {
        isEnableLoginButton =  isEmailValid.value && isPassvalid.value
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrorMsg.value = ""
        }
        else {
            isEmailValid.value = false
            emailErrorMsg.value = "El email no es valido"
        }
        enableLoginButton()
    }

    fun validatepassword() {
        if (password.value.length >= 6) {
            isPassvalid.value = true
            passwordErrorMsg.value = ""
        }
        else {
            isPassvalid.value = false
            passwordErrorMsg.value = "Al menos seis caracteres"
        }
        enableLoginButton()
    }

}