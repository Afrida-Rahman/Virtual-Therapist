package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase

data class RemotePhase(
    @SerializedName("Id")
    val dbIndex: Int,
    @SerializedName("Phase")
    val id: Int,
    @SerializedName("PhaseDialogue")
    val dialogue: String?,
    @SerializedName("HoldInSeconds")
    val holdTime: Int,
    @SerializedName("CapturedImage")
    val imageUrl: String,
    @SerializedName("KeyPointsRestriction")
    val constraints: List<RemoteConstraint>
)

fun RemotePhase.toPhase(): Phase {
    return Phase(
        id = id,
        instruction = dialogue,
        holdTime = holdTime,
        imageUrl = imageUrl,
        constraints = constraints.map { it.toConstraint() },
    )
}