package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mymedicalhub.emmavirtualtherapist.android.core.util.EXERCISE_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.AssessmentListScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.AssessmentListViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.ExerciseListScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.ExerciseListViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.GuidelineScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.GuidelineViewModel

fun NavGraphBuilder.exerciseNav(navController: NavController) {
    lateinit var exerciseListViewModel: ExerciseListViewModel
    lateinit var guidelineViewModel: GuidelineViewModel
    lateinit var assessmentListviewModel: AssessmentListViewModel

    navigation(route = EXERCISE_ROUTE, startDestination = Screen.AssessmentListScreen.route) {
        composable(route = Screen.AssessmentListScreen.route) {
            assessmentListviewModel = hiltViewModel()
            AssessmentListScreen(navController = navController, viewModel = assessmentListviewModel)
        }
        composable(
            route = Screen.ExerciseListScreen.route + "/{tenant}/{testId}/{creationDate}",
            arguments = listOf(
                navArgument(name = "tenant") {
                    type = NavType.StringType
                },
                navArgument(name = "testId") {
                    type = NavType.StringType
                },
                navArgument(name = "creationDate") {
                    type = NavType.StringType
                }
            )
        ) {
            exerciseListViewModel = hiltViewModel()
            it.arguments?.getString("tenant")?.let { tenant ->
                it.arguments?.getString("testId")?.let { testId ->
                    it.arguments?.getString("creationDate")?.let { creationDate ->
                        ExerciseListScreen(
                            tenant = tenant,
                            testId = testId,
                            creationDate = creationDate,
                            navController = navController,
                            viewModel = exerciseListViewModel
                        )
                    }
                }
            }

        }
        composable(
            route = Screen.GuidelineScreen.route + "/{testId}/{exerciseId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                },
                navArgument(name = "exerciseId") {
                    type = NavType.IntType
                }
            )
        ) {
            guidelineViewModel = hiltViewModel()
            it.arguments?.getString("testId")?.also { testId ->
                it.arguments?.getInt("exerciseId")?.let { exerciseId ->
                    GuidelineScreen(
                        testId = testId,
                        exerciseId = exerciseId,
                        navController = navController,
                        guidelineViewModel = guidelineViewModel,
                        exerciseListViewModel = exerciseListViewModel
                    )
                }
            }
        }
        composable(
            route = Screen.ExerciseScreen.route + "/{tenant}/{testId}/{exerciseId}",
            arguments = listOf(
                navArgument(name = "tenant") {
                    type = NavType.StringType
                },
                navArgument(name = "testId") {
                    type = NavType.StringType
                },
                navArgument(name = "exerciseId") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getString("tenant")?.let { tenant ->
                it.arguments?.getString("testId")?.let { testId ->
                    it.arguments?.getInt("exerciseId")?.let { exerciseId ->
//                        ExerciseScreen(
//                            tenant = tenant,
//                            testId = testId,
//                            exerciseId = exerciseId,
//                            navController = navController,
//                            viewModel = viewModel
//                        )

                    }
                }
            }

        }
    }
}