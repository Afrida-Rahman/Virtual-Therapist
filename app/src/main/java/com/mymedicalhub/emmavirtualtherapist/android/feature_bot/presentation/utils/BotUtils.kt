package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils

import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot

object BotUtils {
    fun getBots(): List<Bot> = Bot.values().toList()

    fun getBot(codeName: String): Bot = Bot.fromCodeName(codeName)
}