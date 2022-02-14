package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
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
            HeroSection("Rashed Momin")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                exercise?.let {
                    InstructionSection(it.instruction)
                    it.videoURL?.let { url ->
                        VideoSection(videoUrl = url)
                    }
                    ImageSection(it.imageURLs)
                }
            }
        }
    }
}