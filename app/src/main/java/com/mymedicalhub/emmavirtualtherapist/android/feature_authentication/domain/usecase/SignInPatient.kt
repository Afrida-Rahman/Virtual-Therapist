package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

class SignInPatient {

    suspend operator fun invoke(email: String, password: String, tenant: String): Boolean {
        return true
    }
}