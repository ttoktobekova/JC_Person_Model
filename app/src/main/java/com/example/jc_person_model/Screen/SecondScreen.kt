package com.example.jc_person_model.SecondScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.rememberAsyncImagePainter
import com.example.jc_person_model.model.PersonModel

@Composable
fun SecondScreen(person: PersonModel, onBack: () -> Unit) {
    var age by remember { mutableStateOf(person.age) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Name: ${person.name}", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Age: $age", style = MaterialTheme.typography.bodyLarge)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(person.images.size) { index ->
                val imageUrl = person.images[index]
                if (imageUrl.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = "Image $index",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }

        Button(onClick = { showDialog = true }) {
            Text(text = "Change Age")
        }

        Button(onClick = onBack, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Back")
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(16.dp),
                tonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "Enter new age", style = MaterialTheme.typography.headlineSmall)
                    var newAge by remember { mutableStateOf(age.toString()) }

                    TextField(
                        value = newAge,
                        onValueChange = { newAge = it },
                        label = { Text(text = "Age") }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text(text = "Cancel")
                        }
                        TextButton(onClick = {
                            age = newAge.toIntOrNull() ?: age
                            showDialog = false
                        }) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
}
