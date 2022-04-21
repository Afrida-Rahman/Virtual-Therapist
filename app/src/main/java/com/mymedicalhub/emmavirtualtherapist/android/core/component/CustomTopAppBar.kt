package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R


@Composable
fun CustomTopAppBar(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back",
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
        Image(
            painter = painterResource(id = R.drawable.mmh),
            contentDescription = "MyMedicalHub",
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
        )
    }
}