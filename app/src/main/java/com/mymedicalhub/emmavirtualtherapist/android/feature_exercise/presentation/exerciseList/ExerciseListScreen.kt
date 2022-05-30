package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseScreenActivity
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseDemo
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ManualTrackingForm

@Composable
fun ExerciseListScreen(
    tenant: String,
    testId: String,
    creationDate: String,
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val localConfiguration = LocalConfiguration.current
    viewModel.loadExercises(testId = testId, tenant = tenant)

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
                CustomTopAppBar(
                    leadingIcon = R.drawable.ic_arrow_back,
                    onClickLeadingIcon = {
                        viewModel.onEvent(ExerciseEvent.GoToAssessmentPage)
                        navController.popBackStack()
                    },
                    trailingIcon = R.drawable.search,
                    onClickTrailingIcon = {
                        viewModel.onEvent(ExerciseEvent.ShowExerciseSearchBar)
                    }
                ) {
                    Text(
                        text = "Home Exercises",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
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
                } else if (viewModel.showExerciseDemo.value) {
                    viewModel.getExercise(
                        testId = testId,
                        exerciseId = viewModel.manualSelectedExercise.value
                    )?.let {
                        Log.d("InNavigation", "I am called in exercise list screen")
                        ExerciseDemo(
                            phases = it.phases,
                            onStartButtonClicked = {
                                context.startActivity(
                                    Intent(
                                        context,
                                        ExerciseScreenActivity::class.java
                                    )
                                )
                                viewModel.onEvent(ExerciseEvent.HideExerciseDemo)
                            },
                            onDismiss = { viewModel.onEvent(ExerciseEvent.HideExerciseDemo) }
                        )
                    }
                }

                when {
                    viewModel.isExerciseLoading.value -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    viewModel.showTryAgain.value -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Button(onClick = {
                                viewModel.onEvent(
                                    ExerciseEvent.FetchExercises(
                                        testId = testId,
                                        tenant = tenant
                                    )
                                )
                            }) {
                                Text(text = "Try Again")
                            }
                        }
                    }
                    else -> {
                        viewModel.exercises.value?.let { exercises ->
                            if (exercises.isNotEmpty()) {
                                val itemsPerRow = when {
                                    localConfiguration.screenWidthDp > 840 -> {
                                        3
                                    }
                                    localConfiguration.screenWidthDp > 600 -> {
                                        2
                                    }
                                    else -> {
                                        1
                                    }
                                }
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(itemsPerRow),
                                    modifier = Modifier.padding(4.dp)
                                ) {
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
                                                    Screen.GuidelineScreen.withArgs(
                                                        testId,
                                                        it.id.toString()
                                                    )
                                                )
                                            },
                                            onStartWorkoutButtonClicked = {
                                                viewModel.onEvent(ExerciseEvent.ShowExerciseDemo(it.id))
                                            },
                                            onManualTrackingButtonClicked = {
                                                viewModel.onEvent(
                                                    ExerciseEvent.ManualSelectedExerciseId(
                                                        it.id
                                                    )
                                                )
                                                viewModel.onEvent(ExerciseEvent.ShowManualTrackingAlertDialogue)
                                            }
                                        )
                                    }
                                }
                            } else {
                                Text(
                                    text = "No exercise is assigned yet!",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}