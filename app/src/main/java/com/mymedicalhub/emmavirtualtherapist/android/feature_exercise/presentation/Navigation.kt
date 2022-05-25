package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.content.Intent
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mymedicalhub.emmavirtualtherapist.android.core.util.EXERCISE_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen

fun NavGraphBuilder.exerciseNav(navController: NavController) {
    lateinit var viewModel: ExerciseViewModel
    navigation(route = EXERCISE_ROUTE, startDestination = Screen.AssessmentListScreen.route) {
        composable(route = Screen.AssessmentListScreen.route) {
            viewModel = hiltViewModel()
            AssessmentListScreen(navController = navController, viewModel = viewModel)
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
            it.arguments?.getString("tenant")?.let { tenant ->
                it.arguments?.getString("testId")?.let { testId ->
                    it.arguments?.getString("creationDate")?.let { creationDate ->
                        ExerciseListScreen(
                            tenant = tenant,
                            testId = testId,
                            creationDate = creationDate,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }

        }
        composable(
            route = Screen.ExerciseGuidelineScreen.route + "/{testId}/{exerciseId}",
            arguments = listOf(
                navArgument(name = "testId") {
                    type = NavType.StringType
                },
                navArgument(name = "exerciseId") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getString("testId")?.also { testId ->
                it.arguments?.getInt("exerciseId")?.let { exerciseId ->
                    ExerciseGuidelineScreen(
                        testId = testId,
                        exerciseId = exerciseId,
                        navController = navController,
                        viewModel = viewModel
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
                        Log.d("InNavigation", "I am called in navigation")
                        LocalContext.current.startActivity(
                            Intent(
                                LocalContext.current,
                                ExerciseScreenActivity::class.java
                            )
                        )
                    }
                }
            }

        }
    }
}