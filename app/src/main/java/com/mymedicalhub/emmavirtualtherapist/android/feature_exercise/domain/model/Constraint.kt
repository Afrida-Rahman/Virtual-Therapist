package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

import com.mymedicalhub.emmavirtualtherapist.android.core.util.Draw


interface Constraint {
    fun draw(draw: Draw)
}