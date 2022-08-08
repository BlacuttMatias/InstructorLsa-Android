package com.example.instructorlsa.viewmodels.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.instructorlsa.models.User
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch

class LoginScreenViewModel(application: Context) : ViewModel() {
    private var _userState = MutableLiveData<User>()
    val googleUser: LiveData<User> = _userState

    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState

    init {
        checkSignedInUser(application.applicationContext)
    }

    fun fetchSignInUser(email: String?, name: String?, token: String?) {
        _loadingState.value = true

        viewModelScope.launch {
            val user = User(
                email = email,
                name = name,
                token = token
            )
            InstructorLsaConfig.user = user
            _userState.value = user
        }


        _loadingState.value = false
    }

    private fun checkSignedInUser(applicationContext: Context) {
        showLoading()
        val gsa = GoogleSignIn.getLastSignedInAccount(applicationContext)

        if (gsa != null) {
            val user = User(
                email = gsa.email,
                name = gsa.displayName,
                token = gsa.idToken
            )
            InstructorLsaConfig.user = user
            _userState.value = user
        }
        else{
            hideLoading()
        }
    }

    fun hideLoading() {
        _loadingState.value = false
    }

    fun showLoading() {
        _loadingState.value = true
    }
}