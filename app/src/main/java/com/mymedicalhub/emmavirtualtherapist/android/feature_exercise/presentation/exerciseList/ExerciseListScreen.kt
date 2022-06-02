package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import android.content.Intent
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.CommonViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseScreenActivity
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.component.ExerciseCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.component.ExerciseDemo
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.component.ManualTrackingForm

@Composable
fun ExerciseListScreen(
    tenant: String,
    testId: String,
    creationDate: String,
    navController: NavController,
    commonViewModel: CommonViewModel,
    exerciseListViewModel: ExerciseListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val localConfiguration = LocalConfiguration.current
    commonViewModel.loadExercises(testId = testId, tenant = tenant)

    LaunchedEffect(key1 = true) {
        commonViewModel.eventFlow.collect { event ->
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
            if (exerciseListViewModel.showExerciseSearchBar.value) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = exerciseListViewModel.exerciseSearchTerm.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        onValueChange = { searchTerm ->
                            exerciseListViewModel.onExerciseEvent(
                                ExerciseListEvent.ExerciseSearchTermEntered(
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
                                    exerciseListViewModel.onExerciseEvent(ExerciseListEvent.HideExerciseSearchBar)
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
                        commonViewModel.onExerciseEvent(ExerciseListEvent.GoToAssessmentPage)
                        navController.popBackStack()
                    },
                    trailingIcon = R.drawable.search,
                    onClickTrailingIcon = {
                        commonViewModel.onExerciseEvent(ExerciseListEvent.ShowExerciseSearchBar)
                    }
                ) {
                    Text(
                        text = "Home Exercises",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.surface
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
            if (exerciseListViewModel.showManualTrackingForm.value) {
                commonViewModel.getExercise(
                    testId = testId,
                    exerciseId = exerciseListViewModel.manualSelectedExercise.value
                )?.let { selectedExercise ->
                    ManualTrackingForm(
                        exerciseName = selectedExercise.name,
                        repetitionField = exerciseListViewModel.manualRepetitionCount,
                        onRepetitionValueChanged = {
                            exerciseListViewModel.onExerciseEvent(
                                ExerciseListEvent.ManualRepetitionCountEntered(
                                    it
                                )
                            )
                        },
                        setField = exerciseListViewModel.manualSetCount,
                        onSetValueChanged = {
                            exerciseListViewModel.onExerciseEvent(
                                ExerciseListEvent.ManualSetCountEntered(
                                    it
                                )
                            )
                        },
                        wrongField = exerciseListViewModel.manualWrongCount,
                        onWrongValueChanged = {
                            exerciseListViewModel.onExerciseEvent(
                                ExerciseListEvent.ManualWrongCountEntered(
                                    it
                                )
                            )
                        },
                        onCloseClicked = {
                            exerciseListViewModel.onExerciseEvent(ExerciseListEvent.HideManualTrackingAlertDialogue)
                        },
                        onSaveDataClick = {
                            exerciseListViewModel.onExerciseEvent(
                                ExerciseListEvent.SaveDataButtonClicked(
                                    testId = testId,
                                    exercise = selectedExercise
                                )
                            )
                        },
                        saveDataButtonClickState = exerciseListViewModel.saveDataButtonClicked
                    )
                }
            } else if (exerciseListViewModel.showExerciseDemo.value) {
                commonViewModel.getExercise(
                    testId = testId,
                    exerciseId = exerciseListViewModel.manualSelectedExercise.value
                )?.let {
                    ExerciseDemo(
                        phases = it.phases,
                        onStartButtonClicked = {
                            context.startActivity(
                                Intent(
                                    context,
                                    ExerciseScreenActivity::class.java
                                )
                            )
                            exerciseListViewModel.onExerciseEvent(ExerciseListEvent.HideExerciseDemo)
                        },
                        onDismiss = { exerciseListViewModel.onExerciseEvent(ExerciseListEvent.HideExerciseDemo) }
                    )
                }
            }

            when {
                commonViewModel.isExerciseLoading.value -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                commonViewModel.showTryAgain.value -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(onClick = {
                            commonViewModel.onExerciseEvent(
                                ExerciseListEvent.FetchExercises(
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
                    commonViewModel.exercises.value?.let { exercises ->
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
                                            exerciseListViewModel.onExerciseEvent(
                                                ExerciseListEvent.ShowExerciseDemo(
                                                    it.id
                                                )
                                            )
                                        },
                                        onManualTrackingButtonClicked = {
                                            exerciseListViewModel.onExerciseEvent(
                                                ExerciseListEvent.ManualSelectedExerciseId(
                                                    it.id
                                                )
                                            )
                                            exerciseListViewModel.onExerciseEvent(ExerciseListEvent.ShowManualTrackingAlertDialogue)
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