package com.mymedicalhub.emmavirtualtherapist.android.core.util

const val ROOT_ROUTE = "root"
const val AUTHENTICATION_ROUTE = "authentication"
const val EXERCISE_ROUTE = "exercise"

sealed class Screen(val route: String) {
    object SignInScreen : Screen("sign_in_screen")
    object AssessmentListScreen : Screen("assessment_list_screen")
    object ExerciseListScreen : Screen("exercise_list_screen")
    object ExerciseGuidelineScreen : Screen("exercise_guideline_screen")
    object ExerciseScreen : Screen("exercise_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}