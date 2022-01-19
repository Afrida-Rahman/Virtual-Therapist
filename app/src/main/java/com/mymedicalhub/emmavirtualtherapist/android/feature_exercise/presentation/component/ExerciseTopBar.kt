package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseTopBar(
    title: String,
    navigationIcon: ImageVector,
    onNavigationIconClicked: () -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClicked: () -> Unit = {}
) {
    if (trailingIcon != null) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { onNavigationIconClicked() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = { onTrailingIconClicked() }) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { onNavigationIconClicked() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Go to assessment list screen"
                    )
                }
            }
        )
    }
}