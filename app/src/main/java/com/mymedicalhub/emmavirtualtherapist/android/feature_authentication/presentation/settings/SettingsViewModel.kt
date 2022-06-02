package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    preferences: SharedPreferences
) : ViewModel() {
    private val _patient = mutableStateOf<Patient?>(null)
    val patient: State<Patient?> = _patient

    init {
        _patient.value = Utilities.getPatient(preferences = preferences)
    }
}