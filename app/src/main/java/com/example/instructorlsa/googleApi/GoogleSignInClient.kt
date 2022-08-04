package com.example.instructorlsa.googleApi

import android.content.Context
import com.example.instructorlsa.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

object GoogleSignInClient {
    fun getClient(context: Context): GoogleSignInClient {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.gcp_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context,signInOptions)
    }
}