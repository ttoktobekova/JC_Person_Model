package com.example.jc_person_model.FirstScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.jc_person_model.model.PersonModel

@Composable
fun FirstScreen(onPersonClick: (PersonModel) -> Unit) {
    val persons = PersonModel.getListPerson()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(persons.size) { index ->
            val person = persons[index]
            PersonCard(person = person, onClick = { onPersonClick(person) })
        }
    }
}

@Composable
fun PersonCard(person: PersonModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(person.images.firstOrNull()),
                contentDescription = "Person Icon",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = person.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Age: ${person.age}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
