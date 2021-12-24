package com.mymedicalhub.emmavirtualtherapist.android.core.util

sealed class Screen(val route: String) {
    object SignInScreen : Screen("sign_in_screen")
    object AssessmentListScreen: Screen("assessment_list_screen")
    object ExerciseListScreen: Screen("exercise_list_screen")
    object ExerciseScreen: Screen("exercise_screen")
}
