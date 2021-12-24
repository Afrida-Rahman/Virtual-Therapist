package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun OutlineInputTextField(
    field: State<String>,
    onValueChange: (value: String) -> Unit,
    icon: ImageVector,
    placeholder: String,
    keyboardType: KeyboardType
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = field.value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            trailingIcon = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(imageVector = icon, contentDescription = "")
                }
            },
            singleLine = true,
            placeholder = { Text(placeholder) },
            label = { Text(placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}
