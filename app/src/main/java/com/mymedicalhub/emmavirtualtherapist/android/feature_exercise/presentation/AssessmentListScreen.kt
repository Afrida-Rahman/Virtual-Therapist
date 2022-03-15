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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.AssessmentCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseTopBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.HeroSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.NavigationDrawer
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
    val tenant = "emma"
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
                ExerciseTopBar(
                    title = "Home Exercises",
                    navigationIcon = Icons.Default.Menu,
                    onNavigationIconClicked = {
                        coroutineScope.launch {
                            if (scaffoldState.drawerState.isClosed) {
                                scaffoldState.drawerState.open()
                            } else {
                                scaffoldState.drawerState.close()
                            }
                        }
                    },
                    trailingIcon = Icons.Default.Search,
                    onTrailingIconClicked = {
                        viewModel.onEvent(ExerciseEvent.ShowAssessmentSearchBar)
                    }
                )
            }
        },
        drawerContent = {
            NavigationDrawer(
                onCloseButtonClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            navController.navigate(Screen.AssessmentListScreen.route)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Dashboard,
                        contentDescription = "My Assessments"
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(text = "My Assessments")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            navController.navigate(Screen.SignInScreen.route)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout")
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(text = "Logout")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            HeroSection("Rashed Momin")
            Text(
                text = "My Assessments",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
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
                    modifier = Modifier.padding(4.dp)
                ) {
                    items(viewModel.assessments.value) {
                        AssessmentCard(it) {
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
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
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
}