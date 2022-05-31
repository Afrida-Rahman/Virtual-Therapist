package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.CommonViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseScreenActivity
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.ExerciseListViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.component.ImageSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.component.InstructionSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.component.VideoSection

@Composable
fun GuidelineScreen(
    testId: String,
    exerciseId: Int,
    navController: NavController,
    guidelineViewModel: GuidelineViewModel,
    exerciseListViewModel: ExerciseListViewModel,
    commonViewModel: CommonViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val exercise = commonViewModel.getExercise(testId = testId, exerciseId = exerciseId)
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() }
            ) {
                Text(text = "Exercise Guideline")
            }
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
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
                            navController.popBackStack()
                            context.startActivity(
                                Intent(
                                    context,
                                    ExerciseScreenActivity::class.java
                                )
                            )

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