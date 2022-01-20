package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_list

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseTopBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_list.component.ExerciseCard
import kotlinx.coroutines.flow.collect

@Composable
fun ExerciseListScreen(
    testId: String,
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val focusRequester = remember {
        FocusRequester()
    }
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
            if (viewModel.showSearchBar.value) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.searchTerm.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .focusRequester(focusRequester = focusRequester),
                        onValueChange = { searchTerm ->
                            viewModel.onEvent(
                                ExerciseEvent.SearchTermEntered(
                                    testId = testId,
                                    searchTerm = searchTerm
                                )
                            )
                        },
                        trailingIcon = ({
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close search bar",
                                modifier = Modifier.clickable {
                                    viewModel.onEvent(ExerciseEvent.HideSearchBar)
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
                    onNavigationIconClicked = { navController.popBackStack() },
                    trailingIcon = Icons.Default.Search,
                    onTrailingIconClicked = {
                        viewModel.onEvent(ExerciseEvent.ShowSearchBar)
                        focusRequester.requestFocus()
                    }
                )
            }
        }
    ) {
        Column {
            Row(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(text = "Home Exercises", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (viewModel.exercises.value.isNotEmpty()) {
                LazyColumn {
                    items(viewModel.exercises.value) {
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
                            onManualButtonClicked = {}
                        )
                    }
                }
            } else {
                Text(text = "No exercise is assigned yet!", fontWeight = FontWeight.Bold)
            }
        }
    }
}