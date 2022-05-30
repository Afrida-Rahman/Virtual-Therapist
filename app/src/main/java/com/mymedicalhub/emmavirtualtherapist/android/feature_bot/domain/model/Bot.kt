package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

import androidx.compose.ui.graphics.Color


data class Bot(
    val name: String,
    val codeName: String,
    val icon: Int,
    val backgroundColor: Color
)