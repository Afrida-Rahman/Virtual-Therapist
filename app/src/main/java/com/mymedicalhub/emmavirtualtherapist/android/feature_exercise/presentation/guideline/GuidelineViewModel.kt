package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuidelineViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {

    private val _patient = mutableStateOf<Patient?>(null)
    val patient: State<Patient?> = _patient
}