package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun NavigationDrawer() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {
    EmmaVirtualTherapistTheme {
        NavigationDrawer()
    }
}