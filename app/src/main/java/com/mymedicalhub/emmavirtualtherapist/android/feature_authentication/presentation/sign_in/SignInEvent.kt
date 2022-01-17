package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in

import androidx.navigation.NavController

sealed class SignInEvent {
    data class EnteredEmail(val email: String) : SignInEvent()
    data class EnteredPassword(val password: String) : SignInEvent()
    data class EnteredTenant(val tenant: String) : SignInEvent()
    object ShowPassword : SignInEvent()
    object ShowCircularProgress : SignInEvent()
    class SignInButtonClick(val navController: NavController) : SignInEvent()
}