package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun OutlineInputTextField(
    field: State<String>,
    onValueChange: (value: String) -> Unit,
    icon: Painter? = null,
    onIconPressed: () -> Unit = {},
    placeholder: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
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

@Preview(showBackground = true)
@Composable
fun OutlineInputTextFieldPreview() {
    val field = remember {
        mutableStateOf("")
    }
    EmmaVirtualTherapistTheme {
        OutlineInputTextField(
            field = field,
            onValueChange = {},
            placeholder = "Text field",
            keyboardType = KeyboardType.Text
        )
    }
}