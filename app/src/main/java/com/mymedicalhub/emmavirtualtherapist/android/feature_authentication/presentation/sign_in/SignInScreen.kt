package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.component.OutlineInputTextField
import kotlinx.coroutines.flow.collect

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is SignInViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
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
                        icon = Icons.Default.Home,
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
                        icon = Icons.Default.Email,
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
                        icon = Icons.Default.Password,
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
                            viewModel.onEvent(SignInEvent.SignInButtonClick)
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