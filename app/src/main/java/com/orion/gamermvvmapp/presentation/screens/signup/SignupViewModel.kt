package com.orion.gamermvvmapp.presentation.screens.signup

import android.util.Patterns

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.orion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private  val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases) :ViewModel() {
    // USER Name
    var state by mutableStateOf(SignupState())
        private set

    var isUsernameValid by  mutableStateOf(false)
        private set
    var usarnameErrorMsg by mutableStateOf("")
        private set


    //email

    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrorMsg by mutableStateOf("")
        private set


    var isPassvalid by mutableStateOf(false)
        private set
    var passwordErrorMsg by mutableStateOf("")
        private set
    //BUTTON
    var isEnableLoginButton = false
        private set
    //Confirmar contrseña


    var isconfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrorMsg by mutableStateOf("")
        private set

  var signUpResponse by mutableStateOf<Response<FirebaseUser>?>(null)
      private set

    var user = User()

    fun onEmailImput(email: String) {
        state = state.copy(email = email)
    }

    fun onUserNameImput(username : String) {
        state = state.copy(username = username)
    }

    fun onPasswordInput(pasword: String) {
        state = state.copy(password = pasword)
    }

    fun onConfirmPassword(connfirmPassword: String) {
        state = state.copy(confirmPassword = connfirmPassword)
    }

    fun onSignup() {
        user.username = state.username
        user.email = state.email
        user.password = state.password

        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }
    fun  signup(user: User) = viewModelScope.launch {
       signUpResponse = Response.Loading
        val result = authUseCases.signup(user)
        signUpResponse = result
    }
    fun enableLoginButton() {
        isEnableLoginButton =
            isEmailValid &&
                isPassvalid &&
                isUsernameValid &&
                isconfirmPassword
    }

    fun validateComfirmPassword() {
        if ( state.password == state.confirmPassword) {
            isconfirmPassword = true
            confirmPasswordErrorMsg = ""
        }else {
            isconfirmPassword = false
            confirmPasswordErrorMsg = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid= true
            usarnameErrorMsg = ""
        }else {
            isUsernameValid= false
            usarnameErrorMsg = "Al menos 5 caracteres"
        }
        enableLoginButton()
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrorMsg = ""
        }
        else {
            isEmailValid = false
            emailErrorMsg = "El email no es valido"
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
            passwordErrorMsg = "Al menos seis caracteres"
        }
        enableLoginButton()
    }

}