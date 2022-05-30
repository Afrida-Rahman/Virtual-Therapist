package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuidelineViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {

    private val _patient = Utilities.getPatient(preferences)
    val patient: Patient = _patient
}