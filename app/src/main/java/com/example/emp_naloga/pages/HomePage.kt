package com.example.emp_naloga.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emp_naloga.R
import com.example.emp_naloga.db.Workout
import com.example.emp_naloga.db.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: WorkoutViewModel,
    onWorkoutClick: (Workout) -> Unit
) {
    val workoutList by viewModel.workoutList.observeAsState()

    Column(
        modifier = modifier.fillMaxHeight().padding(8.dp)
    ) {
        Text(
            text = "WORKOUT LOGGER",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(30.dp),
            textAlign = TextAlign.Center,
        )

        workoutList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: Workout ->
                        WorkoutItem(
                            item = item,
                            onDelete = {
                                viewModel.deleteWorkout(item.id)
                            },
                            onClick = { onWorkoutClick(item) }
                        )
                    }
                }
            )
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            text = "No items yet"
        )
    }
}

@Composable
fun WorkoutItem(
    item: Workout,
    onDelete: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/MM", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = "${item.title} - ${item.weight}kg",
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}