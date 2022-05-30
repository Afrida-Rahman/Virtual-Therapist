package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.walkthrough

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalkThroughScreenViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {
    fun onEvent(event: WalkThroughScreenEvent) {
        when (event) {
            is WalkThroughScreenEvent.Finish -> {
                Utilities.savePatient(
                    preferences = preferences, data = Patient(
                        id = null,
                        firstName = "",
                        lastName = "",
                        tenant = "",
                        patientId = "",
                        email = "",
                        loggedIn = false,
                        walkThroughPageShown = true,
                    )
                )
            }
        }
    }
}