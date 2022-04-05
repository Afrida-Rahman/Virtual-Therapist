package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

data class Phase(
    val id: Int,
    val instruction: String?,
    val holdTime: Int,
    val imageUrl: String,
    val constraints: List<Constraint>
)