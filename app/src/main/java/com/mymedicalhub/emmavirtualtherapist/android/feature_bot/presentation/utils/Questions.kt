package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils

enum class Questions(val id: Int) {
    WELCOME(10000012),
    HEIGHT(4),
    WEIGHT(5),
    DOB(6);

    companion object {
        private val map = values().associateBy(Questions::id)
        fun fromInt(id: Int): Questions = map.getValue(id)
    }
}