package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities

data class Message(
    val message: String,
    val userIcon: Int,
    val messageTime: String = Utilities.currentTime()
)