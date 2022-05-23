package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue

@Composable
fun MultiselectItem(text: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(true) }
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = it
                onClick()
            },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Blue,
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = text
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MultiselectItem("Head")
}