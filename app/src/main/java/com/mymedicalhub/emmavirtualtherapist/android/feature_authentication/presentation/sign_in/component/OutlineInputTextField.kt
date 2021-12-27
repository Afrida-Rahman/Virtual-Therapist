package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.component

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun OutlineInputTextField(
    field: State<String>,
    onValueChange: (value: String) -> Unit,
    icon: ImageVector?,
    onIconPressed: () -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        leadingIcon = {
            icon?.let {
                IconButton(
                    onClick = {
                        onIconPressed()
                    },
                ) {
                    Icon(imageVector = icon, contentDescription = "", tint = Color.Green)
                }
            }
        },
        singleLine = true,
        placeholder = { Text(placeholder) },
        label = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}
