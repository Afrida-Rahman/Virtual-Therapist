package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.postureData

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise.ExerciseScreenActivity

@Composable
fun PostureDataScreen(
    navController: NavController,
    viewModel: PostureDataViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val keyboardService = LocalTextInputService.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() }
            ) {
                Text(
                    text = "User Calibration Data",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                OutlineInputTextField(
                    field = viewModel.shoulderToShoulderDistance,
                    placeholder = "Enter shoulder to shoulder distance",
                    label = "Shoulder to shoulder distance",
                    leadingIcon = R.drawable.user_gray,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onEvent(PostureDataEvent.EnteredShoulderToShoulderDistance(it))
                    }
                )
                OutlineInputTextField(
                    field = viewModel.shoulderToElbowDistance,
                    placeholder = "Enter shoulder to elbow distance",
                    label = "Shoulder to elbow distance",
                    leadingIcon = R.drawable.user_gray,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onEvent(PostureDataEvent.EnteredShoulderToElbowDistance(it))
                    }
                )
                OutlineInputTextField(
                    field = viewModel.elbowToWristDistance,
                    placeholder = "Enter elbow to wrist distance",
                    label = " Elbow to wrist distance",
                    leadingIcon = R.drawable.user_gray,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onEvent(PostureDataEvent.EnteredElbowToWristDistance(it))
                    },
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardService?.hideSoftwareKeyboard()
                    })
                )

                Button(
                    onClick = {
                        context.startActivity(
                            Intent(
                                context,
                                ExerciseScreenActivity::class.java
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primaryVariant,
                        disabledBackgroundColor = Color(0xFFEFF6FD)
                    ),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text(
                        text = "Save Data",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}