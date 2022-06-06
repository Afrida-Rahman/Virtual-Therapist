package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.CommonViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.component.AssessmentCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.component.AssessmentFilter

@Composable
fun AssessmentListScreen(
    navController: NavController,
    viewModel: CommonViewModel
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val tenant = viewModel.patient.tenant
    val context = LocalContext.current
    val localConfiguration = LocalConfiguration.current

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
            CustomTopAppBar(
                leadingIcon = R.drawable.menu_new,
                onClickLeadingIcon = {
                    navController.navigate(Screen.SettingsScreen.route)
                },
                trailingIcon = if (viewModel.showAssessmentFilter.value) {
                    R.drawable.ic_cross
                } else {
                    R.drawable.filter
                },
                onClickTrailingIcon = {
                    if (viewModel.showAssessmentFilter.value) {
                        viewModel.onEvent(AssessmentEvent.HideAssessmentFilter)
                    } else {
                        viewModel.onEvent(AssessmentEvent.ShowAssessmentFilter)
                    }
                },
                extraContent = {
                    AnimatedVisibility(visible = viewModel.showAssessmentFilter.value) {
                        AssessmentFilter(
                            testIdField = viewModel.assessmentId,
                            onTestIdValueChanged = {
                                viewModel.onEvent(AssessmentEvent.AssessmentSearchTermEntered(it))
                            }, onClickApply = {
                                viewModel.onEvent(AssessmentEvent.ApplyFilter)
                                viewModel.onEvent(AssessmentEvent.HideAssessmentFilter)
                            }
                        )
                    }
                }
            ) {
                Text(
                    text = "My Assessments",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, selectedIndex = 2)
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        if (viewModel.assessments.value.isNotEmpty()) {
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
                modifier = Modifier.padding(
                    start = 4.dp,
                    top = 4.dp,
                    end = 4.dp,
                    bottom = 56.dp
                )
            ) {
                items(viewModel.assessments.value) {
                    AssessmentCard(it) {
                        if (it.totalExercise > 0) {
                            navController.navigate(
                                Screen.ExerciseListScreen.withArgs(
                                    tenant,
                                    it.testId,
                                    it.creationDate
                                )
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                when {
                    viewModel.isAssessmentLoading.value -> {
                        CircularProgressIndicator()
                    }
                    viewModel.showTryAgain.value -> {
                        Button(
                            onClick = {
                                viewModel.onEvent(AssessmentEvent.FetchAssessments)
                            }
                        ) {
                            Text(text = "Try Again")
                        }
                    }
                    else -> {
                        Text(text = "Opps! No assessment found.")
                    }
                }
            }
        }
    }
}