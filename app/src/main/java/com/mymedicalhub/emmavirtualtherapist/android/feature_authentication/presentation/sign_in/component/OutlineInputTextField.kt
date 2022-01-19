package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun OutlineInputTextField(
    field: State<String>,
    onValueChange: (value: String) -> Unit,
    icon: Painter? = null,
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
        trailingIcon = {
            icon?.let {
                IconButton(
                    onClick = {
                        onIconPressed()
                    },
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(20.dp)
                    )
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
