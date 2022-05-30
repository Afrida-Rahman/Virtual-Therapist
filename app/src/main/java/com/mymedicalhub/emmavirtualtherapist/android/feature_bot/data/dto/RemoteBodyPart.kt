package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.BodyPart

data class RemoteBodyPart(
    @SerializedName("subBodyRegion") val subBodyRegion: String,
    @SerializedName("bodyLocation") val bodyLocation: String,
    @SerializedName("disabled") val disabled: Boolean
)

fun RemoteBodyPart.toBodyPart(): BodyPart = BodyPart(
    subBodyRegion = subBodyRegion,
    bodyLocation = bodyLocation,
    disabled = disabled
)