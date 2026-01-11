package com.example.viikkoteht1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkoteht1.domain.Task
import com.example.viikkoteht1.domain.addTask
import com.example.viikkoteht1.domain.filterByDone
import com.example.viikkoteht1.domain.mockTasks
import com.example.viikkoteht1.domain.sortByDueDate
import com.example.viikkoteht1.domain.toggleDone
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun HomeScreen() {
    var tasklist by remember { mutableStateOf(mockTasks) }
    var showingDoneTasks by remember { mutableStateOf(false) }

    val visibleTasks =
        if (showingDoneTasks) filterByDone(tasklist, true)
        else tasklist

    Column(Modifier.padding(32.dp)) {
        Text(text = "Tasks", style = MaterialTheme.typography.headlineLarge)

        Row() {
            Spacer(Modifier.padding(2.dp))

            Button(onClick = {
                showingDoneTasks = !showingDoneTasks
            }) { Text(if (showingDoneTasks) "Show all" else "Show done") }

            Spacer(Modifier.padding(2.dp))

            Button(onClick = {
                tasklist = sortByDueDate(tasklist)
            }) {
                Text("Sort by Latest")
            }
        }

        Spacer(Modifier.padding(8.dp))

        visibleTasks.forEach { task ->
            Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = task.title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Due: ${task.dueDate}", style = MaterialTheme.typography.bodyMedium)
                }

                Checkbox(
                    checked = task.done,
                    onCheckedChange = {
                        tasklist = toggleDone(tasklist, task.id)
                    })
            }
        }

        Spacer(Modifier.padding(8.dp))

        Button(onClick = {
            val newTask = Task(
                id = tasklist.size + 1,
                title = "New task ${tasklist.size + 1}",
                description = "New task description",
                priority = 1,
                done = false,
                dueDate = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
            )
            tasklist = addTask(tasklist, newTask)
        }) {
            Text("Add task")
        }
    }
}