package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseTopBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.ImageSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.InstructionSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.VideoSection

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            exercise?.let {
                InstructionSection(it.instruction)
                VideoSection(videoUrl = it.videoURL)
                ImageSection(it.imageURLs)
            }
        }
    }
}