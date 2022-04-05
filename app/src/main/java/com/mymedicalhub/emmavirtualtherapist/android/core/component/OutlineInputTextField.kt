package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun OutlineInputTextField(
    field: State<String>,
    onValueChange: (value: String) -> Unit,
    leadingIcon: ImageVector? = null,
    trailingIcon: Painter? = null,
    onIconPressed: () -> Unit = {},
    placeholder: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isEnable: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    if (leadingIcon != null) {
        OutlinedTextField(
            value = field.value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = "Leading Icon")
            },
            trailingIcon = {
                trailingIcon?.let {
                    IconButton(
                        onClick = {
                            onIconPressed()
                        },
                    ) {
                        Icon(
                            painter = trailingIcon,
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
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            enabled = isEnable,
            shape = RoundedCornerShape(16.dp),
            keyboardActions = keyboardActions
        )
    } else {
        OutlinedTextField(
            value = field.value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                trailingIcon?.let {
                    IconButton(
                        onClick = {
                            onIconPressed()
                        },
                    ) {
                        Icon(
                            painter = trailingIcon,
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
            visualTransformation = visualTransformation,
            enabled = isEnable,
            shape = RoundedCornerShape(16.dp)
        )
    }

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