package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

sealed class ExerciseListEvent {
    data class SaveDataButtonClicked(
        val testId: String,
        val exercise: Exercise,
        val repetitionCount: Int,
        val setCount: Int,
        val wrongCount: Int
    ) :
        ExerciseListEvent()
}
