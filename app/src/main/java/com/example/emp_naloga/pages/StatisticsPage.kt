package com.example.emp_naloga.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emp_naloga.R

data class Exercise(
    val name: String,
    val imageRes: Int,
    val youtubeUrl: String
)

@Composable
fun StatisticsPage(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val exercises = listOf(
        Exercise(
            name = "Bench Press",
            imageRes = R.drawable.bench_press,
            youtubeUrl = "https://www.youtube.com/watch?v=gRVjAtPip0Y"
        ),
        Exercise(
            name = "Squat",
            imageRes = R.drawable.squat,
            youtubeUrl = "https://www.youtube.com/watch?v=Dy28eq2PjcM"
        ),
        Exercise(
            name = "Deadlift",
            imageRes = R.drawable.deadlift,
            youtubeUrl = "https://www.youtube.com/watch?v=op9kVnSso6Q"
        ),
        Exercise(
            name = "Shoulder Press",
            imageRes = R.drawable.shoulder_press,
            youtubeUrl = "https://www.youtube.com/watch?v=B-aVuyhvLHU"
        ),
        Exercise(
            name = "Barbell Row",
            imageRes = R.drawable.barbell_row,
            youtubeUrl = "https://www.youtube.com/watch?v=kBWAon7ItDw"
        ),
        Exercise(
            name = "Pull-Ups",
            imageRes = R.drawable.pull_ups,
            youtubeUrl = "https://www.youtube.com/watch?v=eGo4IYlbE5g"
        ),
        Exercise(
            name = "Biceps Curl",
            imageRes = R.drawable.biceps_curl,
            youtubeUrl = "https://www.youtube.com/watch?v=av7-8igSXTs"
        ),
        Exercise(
            name = "Triceps Dip",
            imageRes = R.drawable.triceps_dip,
            youtubeUrl = "https://www.youtube.com/watch?v=0326dy_-CzM"
        ),
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(exercises) { exercise ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(exercise.youtubeUrl))
                        context.startActivity(intent)
                    }
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = exercise.imageRes),
                    contentDescription = exercise.name,
                    modifier = Modifier
                        .size(230.dp)
                        .padding(end = 16.dp)
                )

                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 24.sp
                    )
                )
            }
        }
    }
}


