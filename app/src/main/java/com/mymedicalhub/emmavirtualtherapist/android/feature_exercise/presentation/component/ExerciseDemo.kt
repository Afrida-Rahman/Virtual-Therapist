package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExerciseDemo(
    phases: List<Phase>,
    onStartButtonClicked: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    count = phases.size,
                    modifier = Modifier
                        .height(300.dp)
                        .padding(12.dp)
                ) { index ->
                    val phase = phases[index]
                    phase.instruction?.let {
                        Text(text = "${phase.id}. $it", modifier = Modifier.fillMaxSize())
                    }
                }
                Button(onClick = { onStartButtonClicked() }) {
                    Text(text = "Let's Start")
                }
            }
        }
    }
}