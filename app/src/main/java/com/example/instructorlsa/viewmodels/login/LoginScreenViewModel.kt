package com.example.instructorlsa.viewmodels.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.instructorlsa.models.LoginPostBody
import com.example.instructorlsa.models.User
import com.example.instructorlsa.services.LoginService
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch

class LoginScreenViewModel(application: Context) : ViewModel() {
    var googleUser by mutableStateOf(User(null,null,null, null))

    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState
    val loginService = LoginService()

    init {
        checkSignedInUser(application.applicationContext)
    }

    fun fetchSignInUser(email: String?, token: String?, firstName: String?, lastName: String?) {
        showLoading()
        viewModelScope.launch {
            val body = LoginPostBody(firstName = firstName, lastName = lastName, email = email, token = token)
            val response = loginService.login(body)
            if (response.isSuccessful){
                val user = User(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    token = token
                )
                InstructorLsaConfig.user = user
                googleUser = user
            }
            else{
                //TODO
            }
        }
    }

    private fun checkSignedInUser(applicationContext: Context) {
        showLoading()
        val gsa = GoogleSignIn.getLastSignedInAccount(applicationContext)

        if (gsa != null) {
            viewModelScope.launch{
                val body = LoginPostBody(
                    firstName = gsa.givenName,
                    lastName = gsa.familyName,
                    email = gsa.email,
                    token = gsa.idToken
                )
                val response = loginService.login(body)
                if(response.isSuccessful){
                    val user = User(
                        email = gsa.email,
                        firstName = gsa.givenName,
                        lastName = gsa.familyName,
                        token = gsa.idToken
                    )
                    InstructorLsaConfig.user = user
                    googleUser = user
                }
                else{
                    //TODO
                }
            }
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