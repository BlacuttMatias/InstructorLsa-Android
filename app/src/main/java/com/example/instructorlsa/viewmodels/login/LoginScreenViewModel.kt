package com.example.instructorlsa.viewmodels.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.instructorlsa.mappers.BodyPostLoginMapper
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
    val bodyPostLoginMapper = BodyPostLoginMapper()

    init {
        checkSignedInUser(application.applicationContext)
    }

    private fun fetchLogin(email: String?, token: String?, firstName: String?, lastName: String?){
        viewModelScope.launch {
            try{
                var tokenToUse = token ?: ""
                if (tokenToUse.length > 40) {
                    tokenToUse = tokenToUse.substring(0, 39)
                }
                val user = User(email = email, firstName = firstName, lastName = lastName, token = tokenToUse)
                val body = bodyPostLoginMapper.map(user)
                val response = loginService.login(body)
                if (response.isSuccessful){
                    InstructorLsaConfig.user = user
                    googleUser = user
                }
                else{
                    //TODO
                }
            }
            catch(e: Exception){

            }
        }
    }

    fun fetchSignInUser(email: String?, token: String?, firstName: String?, lastName: String?) {
        showLoading()
        fetchLogin(email = email, token = token, firstName = firstName, lastName = lastName)
    }

    private fun checkSignedInUser(applicationContext: Context) {
        showLoading()
        val gsa = GoogleSignIn.getLastSignedInAccount(applicationContext)

        if (gsa != null) {
            fetchLogin(email = gsa.email, token = gsa.idToken, firstName = gsa.givenName, lastName = gsa.familyName)
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