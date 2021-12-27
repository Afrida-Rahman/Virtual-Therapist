package com.mymedicalhub.emmavirtualtherapist.android.core.util

object Urls {
    fun get(tenant: String): String {
        return when (tenant.lowercase()) {
            "emma" -> ""
            "dev" -> ""
            else -> ""
        }
    }
}