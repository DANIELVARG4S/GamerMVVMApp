package com.orion.gamermvvmapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() :ViewModel() {
    // USER Name
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usarnameErrorMsg: MutableState<String> = mutableStateOf("")


    //email
    var email : MutableState<String> = mutableStateOf( "")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password :MutableState<String> = mutableStateOf( "")
    var isPassvalid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    //BUTTON
    var isEnableLoginButton = false

    //Confirmar contrseña

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isconfirmPassword: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrorMsg: MutableState<String> = mutableStateOf("")

    fun enableLoginButton() {
        isEnableLoginButton =
            isEmailValid.value &&
                isPassvalid.value &&
                isUsernameValid.value &&
                isconfirmPassword.value
    }

    fun validateComfirmPassword() {
        if ( password.value == confirmPassword.value) {
            isconfirmPassword.value = true
            confirmPasswordErrorMsg.value = ""
        }else {
            isconfirmPassword.value = false
            confirmPasswordErrorMsg.value = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

    fun validateUsername() {
        if (username.value.length >= 5) {
            isUsernameValid.value= true
            usarnameErrorMsg.value = ""
        }else {
            isUsernameValid.value= false
            usarnameErrorMsg.value = "Al menos 5 caracteres"
        }
        enableLoginButton()
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