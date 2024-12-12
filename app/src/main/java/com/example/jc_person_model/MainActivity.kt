package com.example.jc_person_model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jc_person_model.FirstScreen.FirstScreen
import com.example.jc_person_model.SecondScreen.SecondScreen
import com.example.jc_person_model.model.PersonModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val currentScreen = remember { mutableStateOf<Screen>(Screen.FirstScreen) }

    when (val screen = currentScreen.value) {
        is Screen.FirstScreen -> {
            FirstScreen { selectedPerson ->
                currentScreen.value = Screen.SecondScreen(selectedPerson)
            }
        }
        is Screen.SecondScreen -> {
            SecondScreen(
                person = screen.person,
                onBack = { currentScreen.value = Screen.FirstScreen }
            )
        }
    }
}

sealed class Screen {
    object FirstScreen : Screen()
    data class SecondScreen(val person: PersonModel) : Screen()
}
