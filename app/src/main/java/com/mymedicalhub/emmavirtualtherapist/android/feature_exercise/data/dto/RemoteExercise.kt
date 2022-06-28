package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

data class RemoteExercise(
    @SerializedName("ExerciseId") val id: Int,
    @SerializedName("ExerciseMedia") val videoUrl: String,
    @SerializedName("ProtocolId") val protocolId: Int,
    @SerializedName("ExerciseName") val name: String,
    @SerializedName("Instructions") val instruction: String,
    @SerializedName("ImageURLs") val imageUrls: List<String>,
    @SerializedName("SetInCount") val totalSet: Int,
    @SerializedName("RepetitionInCount") val repetitionPerSet: Int,
    @SerializedName("FrequencyInDay") val frequency: Int,
    @SerializedName("Phases") val phases: List<RemotePhase>,
    @SerializedName("EvalExerciseProperties") val exerciseProperties: List<RemoteExerciseProperty>
)

fun RemoteExercise.toExercise(): Exercise {
    return Exercise(
        id = id,
        name = name,
        imageURLs = imageUrls,
        videoURL = videoUrl,
        frequency = if (exerciseProperties.isNotEmpty()) {
            exerciseProperties[0].exerciseFrequency
        } else {
            frequency
        },
        repetition = if (exerciseProperties.isNotEmpty()) {
            exerciseProperties[0].repetitionPerSet
        } else {
            repetitionPerSet
        },
        set = if (exerciseProperties.isNotEmpty()) {
            exerciseProperties[0].totalSet
        } else {
            totalSet
        },
        protocolId = protocolId,
        instruction = instruction,
        phases = phases.map { it.toPhase() },
    )
}