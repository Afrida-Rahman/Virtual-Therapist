package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

import androidx.compose.ui.graphics.Color
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue900
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow


enum class Bot(
    val botName: String,
    val codeName: String,
    val icon: Int,
    val backgroundColor: Color
) {
    POSTURE(
        botName = "Postural Bot",
        codeName = "POSTURE_BOT",
        icon = R.drawable.assessments_filled,
        backgroundColor = Blue900
    ),
    MSK(
        botName = "Fysical Score™ Assessment",
        codeName = "MSK_BOT",
        icon = R.drawable.user,
        backgroundColor = Green
    ),
    MSK_RESULT(
        botName = "Posture or Fysical Score™ Results",
        codeName = "PAIN",
        icon = R.drawable.fysical_score,
        backgroundColor = Yellow
    );

    companion object {
        private val map = values().associateBy(Bot::codeName)
        fun fromCodeName(codeName: String): Bot = map.getValue(codeName)
    }
}