package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils

enum class Intents(val value: String) {
    AUTO_CLICK("click_automatic");

    companion object {
        private val map = values().associateBy(Intents::value)
        fun fromInt(value: String): Intents = map.getValue(value)
    }
}