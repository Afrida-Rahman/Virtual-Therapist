package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme


@Composable
fun CustomTopAppBar(
    @DrawableRes leadingIcon: Int,
    onClickLeadingIcon: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
    onClickTrailingIcon: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = { onClickLeadingIcon() }) {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = "Leading Icon",
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
                    .size(40.dp)
                    .padding(12.dp)
            )
        }
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
        trailingIcon?.let {
            IconButton(onClick = { onClickTrailingIcon() }) {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "Trailing Icon",
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = CircleShape
                        )
                        .size(40.dp)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTopAppBarPreview() {
    EmmaVirtualTherapistTheme {
        CustomTopAppBar(
            leadingIcon = R.drawable.menu_new,
            trailingIcon = R.drawable.filter
        ) {
            Image(
                painter = painterResource(id = R.drawable.mmh),
                contentDescription = "MyMedicalHub",
                modifier = Modifier
                    .height(50.dp)
            )
        }
    }
}