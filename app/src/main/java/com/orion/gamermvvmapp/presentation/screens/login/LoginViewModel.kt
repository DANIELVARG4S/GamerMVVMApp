package com.orion.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //email
    var email : MutableState<String> = mutableStateOf( "")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")

    var password :MutableState<String> = mutableStateOf( "")
    var isPassvalid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    //BUTTON
    var isEnableLoginButton = false

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){ //sesion iniciada
            _loginFlow.value = Response.Success(currentUser)
        }
    }
    fun  login()  = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val  result = authUseCases.login(email.value, password.value)
        _loginFlow.value = result
    }

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
            passwordErrorMsg.value = "Al menos 6 caracteres"
        }
        enableLoginButton()
    }

}