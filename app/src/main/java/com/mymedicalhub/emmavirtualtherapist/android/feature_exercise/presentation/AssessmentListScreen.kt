package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.AssessmentCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseTopBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.HeroSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.NavigationDrawer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun AssessmentListScreen(
    navController: NavController,
    viewModel: ExerciseViewModel
) {
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()

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
            ExerciseTopBar(
                title = "My Assessments",
                navigationIcon = Icons.Default.Menu,
                onNavigationIconClicked = {
                    coroutineScope.launch {
                        if (scaffoldState.drawerState.isClosed) {
                            scaffoldState.drawerState.open()
                        } else {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            )
        },
        drawerContent = {
            NavigationDrawer()
        }
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            HeroSection("Rashed Momin")
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "My Assessments",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (viewModel.assessments.value.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        items(viewModel.assessments.value) {
                            AssessmentCard(it) {
                                navController.navigate(
                                    Screen.ExerciseListScreen.withArgs(
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
                                Image(
                                    painter = rememberImagePainter(
                                        data = R.drawable.ic_infinite_loading,
                                        imageLoader = imageLoader
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(150.dp)
                                )
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
}