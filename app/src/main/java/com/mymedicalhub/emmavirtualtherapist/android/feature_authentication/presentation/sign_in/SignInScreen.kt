package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.flow.collect

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

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

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mmh),
                "",
                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Sign In",
                        textAlign = TextAlign.Center,
                        color = Color(0xFF0089FF),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlineInputTextField(
                        field = viewModel.tenant,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredTenant(it))
                        },
                        onIconPressed = {},
                        placeholder = "Tenant",
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlineInputTextField(
                        field = viewModel.email,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredEmail(it))
                        },
                        onIconPressed = {},
                        placeholder = "Email",
                        keyboardType = KeyboardType.Text
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlineInputTextField(
                        field = viewModel.password,
                        onValueChange = {
                            viewModel.onEvent(SignInEvent.EnteredPassword(it))
                        },
                        icon = if (viewModel.showPassword.value) {
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
                    Button(
                        onClick = {
                            viewModel.onEvent(SignInEvent.SignInButtonClick {
                                navController.navigate(Screen.AssessmentListScreen.route)
                            })
                        },
                        modifier = Modifier
                            .fillMaxWidth()
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
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}