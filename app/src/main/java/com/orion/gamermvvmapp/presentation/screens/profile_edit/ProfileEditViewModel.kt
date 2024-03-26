package com.orion.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
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
import com.orion.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.orion.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private  val context:Context
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

    var  saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set


    var imageUri by mutableStateOf("")

    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()


    init {
        state = state.copy(username = user.username)
    }

    fun saveImage() = viewModelScope.launch {
        if (file != null) {
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }
    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")

        if ( result != null) {
            imageUri = result.toString()
            file = ComposeFileProvider.createFileFromUri(context , result)
        }
    }


    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()

        if (result != null) {
            imageUri = ComposeFileProvider.getPathFromBitmap(context, result)
            file = File(imageUri)
        }
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

    fun onUpdate(url: String){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url
        )
        update(myUser)
    }

    fun onUserNameInput(username: String) {
        state = state.copy(username = username)
    }
}