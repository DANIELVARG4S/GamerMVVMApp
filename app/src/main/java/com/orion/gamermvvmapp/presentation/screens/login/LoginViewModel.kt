package com.orion.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //STATE form
    var state by mutableStateOf(LoginState())
        private set

    //email


    var isEmailValid by mutableStateOf(false)
        private set

    var emailErrorMsg: String by mutableStateOf("")
        private set

    var isPassvalid: Boolean by mutableStateOf(false)
    var passwordErrorMsg: String by mutableStateOf("")

    //BUTTON enable
    var isEnableLoginButton = false

   //LOgin Response

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){ //sesion iniciada
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email:String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password:String) {
        state = state.copy(password = password)
    }

    fun  login()  = viewModelScope.launch {
        loginResponse = Response.Loading
        val  result = authUseCases.login(state.email,state.password)
        loginResponse = result
    }

    fun enableLoginButton() {
        isEnableLoginButton =  isEmailValid && isPassvalid
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrorMsg = ""
        }
        else {
            isEmailValid = false
            emailErrorMsg= "El email no es valido"
        }
        enableLoginButton()
    }

    fun validatepassword() {
        if (state.password.length >= 6) {
            isPassvalid = true
            passwordErrorMsg = ""
        }
        else {
            isPassvalid = false
            passwordErrorMsg = "Al menos 6 caracteres"
        }
        enableLoginButton()
    }

}