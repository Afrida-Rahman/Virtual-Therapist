package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseTopBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.HeroSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ManualTrackingForm

@Composable
fun ExerciseListScreen(
    testId: String,
    creationDate: String,
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    viewModel.searchExercises(testId = testId)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is UIEvent.ShowToastMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (viewModel.showExerciseSearchBar.value) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.exerciseSearchTerm.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        onValueChange = { searchTerm ->
                            viewModel.onEvent(
                                ExerciseEvent.ExerciseSearchTermEntered(
                                    testId = testId,
                                    searchTerm = searchTerm
                                )
                            )
                        },
                        leadingIcon = ({
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search icon"
                            )
                        }),
                        trailingIcon = ({
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close search bar",
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(ExerciseEvent.HideExerciseSearchBar)
                                }
                            )
                        }),
                        singleLine = true,
                        textStyle = TextStyle(color = MaterialTheme.colors.primary)
                    )
                }
            } else {
                ExerciseTopBar(
                    title = "Home Exercises",
                    navigationIcon = Icons.Default.ArrowBackIos,
                    onNavigationIconClicked = {
                        viewModel.onEvent(ExerciseEvent.GoToAssessmentPage)
                        navController.popBackStack()
                    },
                    trailingIcon = Icons.Default.Search,
                    onTrailingIconClicked = {
                        viewModel.onEvent(ExerciseEvent.ShowExerciseSearchBar)
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            HeroSection("Rashed Momin")
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Home Exercises",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = testId, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
                        Text(text = creationDate, fontSize = 14.sp)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (viewModel.showManualTrackingForm.value) {
                    Dialog(onDismissRequest = { viewModel.onEvent(ExerciseEvent.HideManualTrackingAlertDialogue) }) {
                        viewModel.getExercise(
                            testId = testId,
                            exerciseId = viewModel.manualSelectedExercise.value
                        )?.let { selectedExercise ->
                            ManualTrackingForm(
                                exerciseName = selectedExercise.name,
                                repetitionField = viewModel.manualRepetitionCount,
                                onRepetitionValueChanged = {
                                    viewModel.onEvent(ExerciseEvent.ManualRepetitionCountEntered(it))
                                },
                                setField = viewModel.manualSetCount,
                                onSetValueChanged = {
                                    viewModel.onEvent(ExerciseEvent.ManualSetCountEntered(it))
                                },
                                wrongField = viewModel.manualWrongCount,
                                onWrongValueChanged = {
                                    viewModel.onEvent(ExerciseEvent.ManualWrongCountEntered(it))
                                },
                                onCloseClicked = {
                                    viewModel.onEvent(ExerciseEvent.HideManualTrackingAlertDialogue)
                                },
                                onSaveDataClick = {
                                    viewModel.onEvent(
                                        ExerciseEvent.SaveDataButtonClicked(
                                            testId = testId,
                                            exercise = selectedExercise
                                        )
                                    )
                                },
                                saveDataButtonClickState = viewModel.saveDataButtonClicked
                            )
                        }
                    }
                }
                viewModel.exercises.value?.let { exercises ->
                    if (exercises.isNotEmpty()) {
                        LazyColumn {
                            items(exercises) {
                                ExerciseCard(
                                    imageUrl = if (it.imageURLs.isNotEmpty()) {
                                        it.imageURLs[0]
                                    } else null,
                                    name = it.name,
                                    repetition = it.repetition,
                                    set = it.set,
                                    isActive = true,
                                    onGuidelineButtonClicked = {
                                        navController.navigate(
                                            Screen.ExerciseGuidelineScreen.withArgs(
                                                testId,
                                                it.id.toString()
                                            )
                                        )
                                    },
                                    onStartWorkoutButtonClicked = {
                                        navController.navigate(
                                            Screen.ExerciseScreen.withArgs(
                                                testId,
                                                it.id.toString()
                                            )
                                        )
                                    },
                                    onManualTrackingButtonClicked = {
                                        viewModel.onEvent(ExerciseEvent.ManualSelectedExerciseId(it.id))
                                        viewModel.onEvent(ExerciseEvent.ShowManualTrackingAlertDialogue)
                                    }
                                )
                            }
                        }
                    } else {
                        Text(text = "No exercise is assigned yet!", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}