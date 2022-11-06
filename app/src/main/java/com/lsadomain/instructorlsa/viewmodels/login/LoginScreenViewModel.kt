package com.lsadomain.instructorlsa.viewmodels.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.lsadomain.instructorlsa.mappers.BodyPostLoginMapper
import com.lsadomain.instructorlsa.models.User
import com.lsadomain.instructorlsa.services.LoginService
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.launch

class LoginScreenViewModel(application: Context) : ViewModel() {
    var googleUser by mutableStateOf(User(null,null,null, null))

    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState
    val loginService = LoginService()
    val bodyPostLoginMapper = BodyPostLoginMapper()
    var isError by mutableStateOf(false)

    init {
        checkSignedInUser(application.applicationContext)
    }

    private fun fetchLogin(email: String?, token: String?, firstName: String?, lastName: String?){
        viewModelScope.launch {
            try{
                var tokenToUse = token ?: ""
                if (tokenToUse.length > 40) {
                    tokenToUse = tokenToUse.substring(tokenToUse.length - 40, tokenToUse.length)
                }
                val user = User(email = email, firstName = firstName, lastName = lastName, token = tokenToUse)
                val body = bodyPostLoginMapper.map(user)
                val response = loginService.login(body)
                if (response.isSuccessful){
                    InstructorLsaConfig.user = user
                    googleUser = user
                }
                else{
                    showError()
                    Log.d("Error", "1123333333333")
                }
            }
            catch(e: Exception){
                showError()
                Log.d("Excepcion", "1123333333333")
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
            fetchLogin(email = gsa.email, token = gsa.id, firstName = gsa.givenName, lastName = gsa.familyName)
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

    fun showError() {
        isError = true
        hideLoading()
    }
}