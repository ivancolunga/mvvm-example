package com.example.mvvmexample.presentation.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvvmexample.data.model.NoteEntity
import com.example.mvvmexample.data.repositoryimpl.NoteRepositoryImpl
import com.example.mvvmexample.presentation.util.NoteDatabaseProvider
import com.example.mvvmexample.presentation.routes.Screen

@Composable
fun NotesScreen(navController: NavHostController) {

    val viewModel: NotesViewModel = viewModel(
        factory = NotesViewModelFactory(
            NoteRepositoryImpl(
                NoteDatabaseProvider.provideNoteDatabase(LocalContext.current).notesDao
            )
        )
    )

    viewModel.getNotes()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Mis Notas")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.AddNote.name) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar nota")
            }
        }
    ) {

        when (val currentState = viewModel.state.observeAsState().value) {

            is NotesState.EmptyState -> EmptyState()

            is NotesState.Success -> NoteList(notes = currentState.notes)

            null -> {}
        }
    }
}

@Composable
fun NoteList(notes: List<NoteEntity>) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        notes.forEach { note ->
            NoteItem(note)
        }
    }
}

@Composable
fun NoteItem(note: NoteEntity) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = note.title,
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(top = 4.dp))
            Text(
                text = note.content,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Agrega una nota.")
    }
}

@Preview
@Composable
fun DefaultNoteScreen() {
    val navController = rememberNavController()
    NotesScreen(navController)
}