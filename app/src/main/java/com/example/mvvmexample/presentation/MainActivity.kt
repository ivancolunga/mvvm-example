package com.example.mvvmexample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmexample.presentation.addnote.CreateNoteScreen
import com.example.mvvmexample.presentation.notes.NotesScreen
import com.example.mvvmexample.presentation.routes.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Notes.name) {
                composable(Screen.Notes.name) { NotesScreen(navController) }
                composable(Screen.AddNote.name) { CreateNoteScreen(navController) }
            }
        }
    }
}