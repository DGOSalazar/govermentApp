package com.example.govermentapp.ui.presession

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.govermentapp.data.models.entities.FirebaseState
import com.example.govermentapp.domain.usescase.OnLoginUseCase
import com.example.govermentapp.domain.usescase.OnRegisterUseCase
import com.example.govermentapp.domain.usescase.SaveAndGetUserDataUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.isSensitiveHeader
import javax.inject.Inject

@HiltViewModel
class PreSessionViewModel @Inject constructor(
    private val onRegisterUseCase: OnRegisterUseCase,
    private val onLoginUseCase: OnLoginUseCase,
    private val sharedPreference: SaveAndGetUserDataUseCase
) : ViewModel() {

    private val _registerStatus = MutableLiveData<FirebaseState<FirebaseUser>>()
    val registerStatus: LiveData<FirebaseState<FirebaseUser>> = _registerStatus

    private val _loginStatus = MutableLiveData<FirebaseState<FirebaseUser>>()
    val loginStatus: LiveData<FirebaseState<FirebaseUser>> = _loginStatus

    fun setRegisterUser(email: String, pass: String) {
        _registerStatus.value = FirebaseState.loading(null)
        viewModelScope.launch {
            val res = onRegisterUseCase(email,pass)
            _registerStatus.postValue(res)
        }
    }
    fun getLoginUser(email: String, pass: String) {
        _loginStatus.value = FirebaseState.loading(null)
        viewModelScope.launch {
            val res = onLoginUseCase(email,pass)
            _loginStatus.postValue(res)
        }
    }
    fun saveLocalInfoUser(email: String, pass: String, isBiometric: Boolean) {
        sharedPreference.saveUserData(email,pass,isBiometric)
    }
    fun getEmail() = sharedPreference.getEmail()
    fun getPass() = sharedPreference.getPass()
    fun isBiometric() = sharedPreference.getBiometric()
    fun clearSharedPreference() = sharedPreference.clearData()
}