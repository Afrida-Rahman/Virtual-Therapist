package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.splash

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    preferences: SharedPreferences
) : ViewModel() {
    private val _isAlreadyLoggedIn = mutableStateOf(false)
    val isAlreadyLoggedIn: State<Boolean> = _isAlreadyLoggedIn

    init {
        val data = Utilities.getPatient(preferences = preferences)
        if (data.firstName.isNotEmpty() and data.patientId.isNotEmpty()) {
            _isAlreadyLoggedIn.value = true
        }
    }
}