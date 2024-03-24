package com.orion.gamermvvmapp.presentation.screens.profile_edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.gamermvvmapp.domain.model.Response
import com.orion.gamermvvmapp.domain.model.User
import com.orion.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases
): ViewModel(){

    //STAte Form
    var  state by mutableStateOf(ProfileEditState())
        private set

    var  usarnameErrorMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")

    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set


    var imageUri by mutableStateOf<Uri?>(null)

    var hasImage by mutableStateOf(false)

    fun  onCameraResult(result: Boolean) {

        hasImage = result
    }
    fun onGaleyResult(uri: Uri?) {
        hasImage = uri != null
        imageUri = uri
    }
    init {
        state = state.copy(username = user.username)
    }

    fun  validateUsernam() {
        if (state.username.length >= 5) {
            usarnameErrorMsg = ""
        }
        else {
            usarnameErrorMsg = "Al menos 5 caracteres"
        }
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

    fun onUpdate(){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = ""
        )
        update(myUser)
    }

    fun onUserNameInput(username: String) {
        state = state.copy(username = username)
    }
}