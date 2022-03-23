package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.*

@Composable
fun ExerciseGuidelineScreen(
    testId: String,
    exerciseId: Int,
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val exercise = viewModel.getExercise(testId = testId, exerciseId = exerciseId)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ExerciseTopBar(
                title = "Exercise Guideline",
                navigationIcon = Icons.Default.ArrowBackIos,
                onNavigationIconClicked = { navController.popBackStack() }
            )
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            viewModel.patient.value?.let {
                HeroSection("${it.firstName} ${it.lastName}")
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                exercise?.let { exercise ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = exercise.name,
                            style = MaterialTheme.typography.h1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Button(onClick = {
                            viewModel.patient.value?.let { patient ->
                                navController.popBackStack()
                                navController.navigate(
                                    Screen.ExerciseScreen.withArgs(
                                        patient.tenant,
                                        testId,
                                        exercise.id.toString()
                                    )
                                )
                            }

                        }) {
                            Text(text = "Start Workout")
                        }
                    }
                    InstructionSection(exercise.instruction)
                    exercise.videoURL?.let { url ->
                        VideoSection(videoUrl = url)
                    }
                    ImageSection(exercise.imageURLs)
                }
            }
        }
    }
}