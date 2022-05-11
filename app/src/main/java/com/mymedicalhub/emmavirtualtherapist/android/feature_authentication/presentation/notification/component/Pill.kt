package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.notification.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow

@OptIn(ExperimentalUnitApi::class)
@Composable
fun Pill(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit(9f, TextUnitType.Sp),
        modifier = Modifier
            .padding(3.dp)
            .background(color = Yellow, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp)
    )
}

@Preview(showBackground = false)
@Composable
fun PillPreview() {
    EmmaVirtualTherapistTheme {
        Pill("Region: Legs")
    }
}