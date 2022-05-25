package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils

import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow

object BotUtils {
    fun getBots(): List<Bot> = listOf(
        Bot(
            name = "Postural Assessment",
            codeName = "POSTURE_BOT",
            icon = R.drawable.posture_screening,
            backgroundColor = Blue
        ),
        Bot(
            name = "Fysical Score™ Assessment",
            codeName = "MSK_BOT",
            icon = R.drawable.user,
            backgroundColor = Green
        ),
        Bot(
            name = "Posture or Fysical Score™ Results",
            codeName = "PAIN",
            icon = R.drawable.fysical_score,
            backgroundColor = Yellow
        )
    )

    fun getBot(codeName: String): Bot = getBots().find { it.codeName == codeName } ?: getBots()[0]
}