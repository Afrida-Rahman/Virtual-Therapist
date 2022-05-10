package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.NavigationDrawer
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.AssessmentCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AssessmentListScreen(
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val tenant = viewModel.patient.value?.tenant ?: "emma"
    val context = LocalContext.current
    val localConfiguration = LocalConfiguration.current
    val coroutineScope = rememberCoroutineScope()

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
            if (viewModel.showAssessmentSearchBar.value) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.assessmentSearchTerm.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White),
                        onValueChange = { searchTerm ->
                            viewModel.onEvent(
                                ExerciseEvent.AssessmentSearchTermEntered(searchTerm)
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
                                    viewModel.onEvent(ExerciseEvent.HideAssessmentSearchBar)
                                }
                            )
                        }),
                        singleLine = true,
                        textStyle = TextStyle(color = MaterialTheme.colors.primary)
                    )
                }
            } else {
                CustomTopAppBar(
                    leadingIcon = R.drawable.menu_new,
                    onClickLeadingIcon = {
                        coroutineScope.launch {
                            if (scaffoldState.drawerState.isClosed) {
                                scaffoldState.drawerState.open()
                            } else {
                                scaffoldState.drawerState.close()
                            }
                        }
                    },
                    trailingIcon = R.drawable.filter,
                    onClickTrailingIcon = {
                        viewModel.onEvent(ExerciseEvent.ShowAssessmentSearchBar)
                    }
                ) {
                    Text(text = "My Assessments")
                }
            }
        },
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            ) {
                viewModel.onEvent(ExerciseEvent.SignOut)
                navController.popBackStack()
                navController.navigate(Screen.SignInScreen.route)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, selectedIndex = 2)
        }
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
                cells = GridCells.Fixed(itemsPerRow),
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
                                viewModel.onEvent(ExerciseEvent.FetchAssessments)
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