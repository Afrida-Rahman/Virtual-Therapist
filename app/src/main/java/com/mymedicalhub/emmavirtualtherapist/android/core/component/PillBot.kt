package com.mymedicalhub.emmavirtualtherapist.android.core.component

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
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.GreenBackground

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PillBot(text: String) {
    Text(
        text = text,
        color = Green,
        style = MaterialTheme.typography.caption,
        fontWeight = FontWeight.W700,
        fontSize = TextUnit(12f, TextUnitType.Sp),
        modifier = Modifier
            .padding(8.dp)
            .background(color = GreenBackground, shape = RoundedCornerShape(0.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp)
    )
}

@Preview(showBackground = false)
@Composable
fun PillPreview() {
    EmmaVirtualTherapistTheme {
        PillBot("General")
    }
}