package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BotViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {
    fun onEvent(event: BotEvent) {
        when (event) {
            is BotEvent.SignOut -> {
                Utilities.savePatient(
                    preferences = preferences, data = Patient(
                        id = null,
                        tenant = "",
                        patientId = "",
                        firstName = "",
                        lastName = "",
                        loggedIn = false
                    )
                )
            }
        }
    }
}