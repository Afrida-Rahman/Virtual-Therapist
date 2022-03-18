package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        if (viewModel.isAlreadyLoggedIn.value) {
            navController.popBackStack()
            navController.navigate(Screen.AssessmentListScreen.route)
        }
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

    Scaffold(scaffoldState = scaffoldState) {
        if (viewModel.isAlreadyLoggedIn.value) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = CircleShape
                                )
                                .size(40.dp)
                                .padding(12.dp)
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.mmh),
                        contentDescription = "MyMedicalHub",
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Select Your Practice")
                    OutlineInputTextField(
                        field = viewModel.tenant,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredTenant(it))
                        },
                        onIconPressed = {},
                        placeholder = "Tenant",
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Email Address")
                    OutlineInputTextField(
                        field = viewModel.email,
                        leadingIcon = Icons.Default.Email,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredEmail(it))
                        },
                        onIconPressed = {},
                        placeholder = "Email",
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Password")
                    OutlineInputTextField(
                        field = viewModel.password,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredPassword(it))
                        },
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = if (viewModel.showPassword.value) {
                            painterResource(id = R.drawable.ic_eye_open)
                        } else {
                            painterResource(id = R.drawable.ic_eye_closed)
                        },
                        onIconPressed = {
                            viewModel.onEvent(SignInEvent.ShowPassword)
                        },
                        placeholder = "Password",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = if (viewModel.showPassword.value) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Forget Password?",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            viewModel.onEvent(SignInEvent.SignInButtonClick {
                                navController.navigate(Screen.AssessmentListScreen.route)
                            })
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30.dp))
                    ) {
                        if (viewModel.showCircularProgressIndicator.value) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        } else {
                            Text(
                                text = "Sign In",
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Text(text = "Don't have account? ")
                        Text(
                            text = "Sign Up",
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}