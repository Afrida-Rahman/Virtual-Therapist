package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase

data class RemotePhase(
    @SerializedName("Phase")
    val id: Int,
    @SerializedName("PhaseDialogue")
    val dialogue: String?,
    @SerializedName("HoldInSeconds")
    val holdTime: Int,
    @SerializedName("KeyPointsRestriction")
    val constraints: List<RemoteConstraint>
)

fun RemotePhase.toPhase(): Phase {
    return Phase(
        id = id,
        instruction = dialogue,
        holdTime = holdTime,
        constraints = constraints.map { it.toConstraint() },
    )
}