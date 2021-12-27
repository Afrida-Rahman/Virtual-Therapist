package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase.PatientUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val patientUseCases: PatientUseCases
) : ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _tenant = mutableStateOf("")
    val tenant: State<String> = _tenant

    private val _showCircularProgress = mutableStateOf(false)
    val showCircularProgress: State<Boolean> = _showCircularProgress

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EnteredEmail -> {
                _email.value = event.email
            }
            is SignInEvent.EnteredPassword -> {
                _password.value = event.password
            }
            is SignInEvent.EnteredTenant -> {
                _tenant.value = event.tenant
            }
            is SignInEvent.ShowPassword -> {
                _showPassword.value = !showPassword.value
            }
            is SignInEvent.ShowCircularProgress -> {
                _showCircularProgress.value = !showCircularProgress.value
            }
            is SignInEvent.SignInButtonClick -> {
                viewModelScope.launch {
                    if (patientUseCases.signInPatient(
                            email = email.value,
                            password = password.value,
                            tenant = tenant.value
                        )
                    ) {
//                        Toast.makeText(context, "Successfully logged in!", Toast.LENGTH_LONG).show()
                        Log.d("SignInViewModel", "Successfully logged in!")
                    } else {
                        Log.d("SignInViewModel", "Email or password did not match!")
//                        Toast.makeText(
//                            context,
//                            "Email or password did not match!",
//                            Toast.LENGTH_LONG
//                        ).show()
                    }
                }
            }
        }
    }
}
