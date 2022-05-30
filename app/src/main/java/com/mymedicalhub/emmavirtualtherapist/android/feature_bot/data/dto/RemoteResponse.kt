package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response

data class RemoteResponse(
    @SerializedName("checked") val checked: Boolean,
    @SerializedName("color") val color: String,
    @SerializedName("description") val description: String,
    @SerializedName("hint") val hint: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("modalUrl") val modalUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("referenceId") val referenceId: Int,
    @SerializedName("title") val title: String
)

fun RemoteResponse.toResponse(): Response {
    return Response(
        checked = checked,
        color = color,
        description = description,
        hint = hint,
        icon = icon,
        id = id,
        modalUrl = modalUrl,
        name = name,
        referenceId = referenceId,
        title = title
    )
}
