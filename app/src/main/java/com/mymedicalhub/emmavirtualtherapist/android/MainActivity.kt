package com.mymedicalhub.emmavirtualtherapist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.SignInScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.*
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmmaVirtualTherapistTheme {
                val navController = rememberNavController()
                lateinit var viewModel: ExerciseViewModel
                NavHost(
                    navController = navController,
                    startDestination = Screen.AssessmentListScreen.route
                ) {
                    composable(route = Screen.SignInScreen.route) {
                        SignInScreen(navController = navController)
                    }
                    composable(route = Screen.AssessmentListScreen.route) {
                        viewModel = hiltViewModel()
                        AssessmentListScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(
                        route = Screen.ExerciseListScreen.route + "/{testId}/{creationDate}",
                        arguments = listOf(
                            navArgument(name = "testId") {
                                type = NavType.StringType
                            },
                            navArgument(name = "creationDate") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        it.arguments?.getString("testId")?.let { testId ->
                            it.arguments?.getString("creationDate")?.let { creationDate ->
                                ExerciseListScreen(
                                    testId = testId,
                                    creationDate = creationDate,
                                    navController = navController,
                                    viewModel = viewModel
                                )
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
                        route = Screen.ExerciseScreen.route + "/{testId}/{exerciseId}",
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
                                ExerciseScreen(
                                    testId = testId,
                                    exerciseId = exerciseId,
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}