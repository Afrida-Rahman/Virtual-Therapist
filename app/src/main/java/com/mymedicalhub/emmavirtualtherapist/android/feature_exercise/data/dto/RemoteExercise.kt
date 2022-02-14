package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

data class RemoteExercise(
    @SerializedName("ExerciseId")
    val id: Int,
    @SerializedName("ExerciseMedia")
    val videoUrl: String,
    @SerializedName("ExerciseName")
    val name: String,
    @SerializedName("FrequencyInDay")
    val frequency: Int,
    @SerializedName("HoldInSeconds")
    val holdTime: Int,
    @SerializedName("ImageURLs")
    val imageURLs: List<String>,
    @SerializedName("Instructions")
    val instruction: String,
    @SerializedName("IsPhaseFinished")
    val isLastPhase: Boolean,
    @SerializedName("KeyPointsRestrictionGroup")
    val remotePhases: List<RemotePhase>,
    @SerializedName("ProtocolId")
    val protocolId: Int,
    @SerializedName("RepetitionInCount")
    val repetitionPerSet: Int,
    @SerializedName("SetInCount")
    val totalSet: Int
)

fun RemoteExercise.toExercise(): Exercise {
    return Exercise(
        id = id,
        name = name,
        imageURLs = imageURLs,
        videoURL = videoUrl,
        frequency = frequency,
        repetition = repetitionPerSet,
        set = totalSet,
        protocolId = protocolId,
        instruction = instruction,
        phases = remotePhases.map { it.toPhase() },
    )
}