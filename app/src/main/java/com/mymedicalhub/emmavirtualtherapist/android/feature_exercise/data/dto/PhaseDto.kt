package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase

data class PhaseDto(
    @SerializedName("ExerciseId") val exerciseId: Int,
    @SerializedName("Phases") val phases: List<RemotePhase>
)

fun PhaseDto.toPhaseList(): List<Phase> = phases.map { it.toPhase() }