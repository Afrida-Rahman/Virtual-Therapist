package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.Constraint

data class Phase(
    val id: Int,
    val instruction: String?,
    val holdTime: Int,
    val constraints: List<Constraint>
)