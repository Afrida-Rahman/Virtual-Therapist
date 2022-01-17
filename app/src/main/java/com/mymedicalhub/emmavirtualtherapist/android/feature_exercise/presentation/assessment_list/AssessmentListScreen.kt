package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessment_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessment_list.component.AssessmentCard
import kotlinx.coroutines.flow.collect

@Composable
fun AssessmentListScreen(
    viewModel: ExerciseViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "My Assessments",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (viewModel.assessments.value.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items(viewModel.assessments.value) {
                        AssessmentCard(
                            Assessment(
                                testId = it.testId,
                                creationDate = it.creationDate,
                                isReportReady = it.isReportReady,
                                providerId = it.providerId,
                                providerName = it.providerName,
                                bodyRegionId = it.bodyRegionId,
                                bodyRegionName = it.bodyRegionName,
                                registrationType = it.registrationType,
                                exercises = it.exercises,
                            )
                        )
                    }
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    when {
                        viewModel.isAssessmentLoading.value -> {
                            Text(text = "Loading...")
                        }
                        viewModel.showTryAgain.value -> {
                            Button(
                                onClick = {
                                    viewModel.onEvent(ExerciseEvent.FetchAssessments)
                                }
                            ) {
                                Text(text = "Try Again")
                            }
                        }
                        else -> {
                            Text(text = "This patient do not have any assigned assessment yet.")
                        }
                    }
                }
            }
        }
    }
}