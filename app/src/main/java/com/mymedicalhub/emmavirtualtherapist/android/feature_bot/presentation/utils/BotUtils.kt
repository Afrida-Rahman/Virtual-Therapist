package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils

import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot

object BotUtils {
    fun getBots(): List<Bot> = listOf(
        Bot(
            name = "Pain Recorder Bot",
            codeName = "PAIN",
            icon = R.drawable.chest_pain
        ),
        Bot(
            name = "Fysical Score Assessment",
            codeName = "PAIN",
            icon = R.drawable.exercise
        ),
        Bot(
            name = "Postural Assessment",
            codeName = "PAIN",
            icon = R.drawable.posture
        )
    )

    fun getBot(codeName: String): Bot? = getBots().find { it.codeName == codeName }
}