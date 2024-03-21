package com.orion.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel  @Inject constructor(): ViewModel(){

    //STAte Form
    var  state by mutableStateOf(ProfileEditState())
        private set

    var  usarnameErrorMsg by mutableStateOf("")
        private set


    fun  validateUsernam() {
        if (state.username.length >= 5) {
            usarnameErrorMsg = ""
        }
        else {
            usarnameErrorMsg = "Al menos 5 caracteres"
        }
    }
    fun onUserNameInput(username: String) {
        state = state.copy(username = username)
    }
}