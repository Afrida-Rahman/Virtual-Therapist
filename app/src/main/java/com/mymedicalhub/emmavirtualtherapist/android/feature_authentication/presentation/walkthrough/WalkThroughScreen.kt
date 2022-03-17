package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.walkthrough

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.WalkThroughPage
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WalkThroughScreen(pages: List<WalkThroughPage>) {
    HorizontalPager(count = pages.size) { index ->
        val page = pages[index]
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(
                text = "Skip",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            )
            Card(
                modifier = Modifier
                    .background(
                        Color.White, RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
                    .offset(y = (-150).dp),
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(
                            Color.White, RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 30.dp,
                                bottomEnd = 0.dp,
                                bottomStart = 0.dp
                            )
                        )
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.mmh),
                        contentDescription = "MyMedicalHUB",
                        modifier = Modifier
                            .width(150.dp)
                    )
                    Image(
                        painter = painterResource(id = page.image),
                        contentDescription = "Page Image",
                        modifier = Modifier.size(250.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.5f)
            ) {
                Text(
                    text = page.title,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 24.dp)
                )
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 24.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    for (i in pages.indices) {
                        val color = if (index == i) {
                            Color.Blue
                        } else {
                            Color.Gray
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .width(40.dp)
                                .height(4.dp)
                                .background(color = color, shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
            Button(
                onClick = {}, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            ) {
                Text(text = "Next", modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WalkThroughScreenPreview() {
    EmmaVirtualTherapistTheme {
        WalkThroughScreen(
            pages = listOf(
                WalkThroughPage(
                    image = R.drawable.chest_pain,
                    title = "Providing a revolutionary approach to musculoskeletal",
                    description = "Our health technology solution enables physicians to identify patient risk for injuring any of the ove 350 joints in the human body"
                ),
                WalkThroughPage(
                    image = R.drawable.chest_pain,
                    title = "Patient Experience Journey",
                    description = "Fysical Score Assessment identifies and quantifies risk factors that are predisposing people to catastrophic injuries."
                ),
                WalkThroughPage(
                    image = R.drawable.chest_pain,
                    title = "AI-powered, ever-leaning engine to beat",
                    description = "My Medical Hub is a cloud-based, predictive healthcare informatics and technology health/wellness services company."
                )
            )
        )
    }
}