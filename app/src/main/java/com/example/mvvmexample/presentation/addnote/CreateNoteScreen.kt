package com.example.mvvmexample.presentation.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvvmexample.data.model.NoteEntity
import com.example.mvvmexample.data.repositoryimpl.NoteRepositoryImpl
import com.example.mvvmexample.presentation.util.NoteDatabaseProvider


@Composable
fun CreateNoteScreen(navController: NavHostController) {
    val viewModel: CreateNoteViewModel = viewModel(
        factory = CreateNoteViewModelFactory(
            NoteRepositoryImpl(
                NoteDatabaseProvider.provideNoteDatabase(LocalContext.current).notesDao
            )
        )
    )

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Crear nota")
            })
        },
        scaffoldState = scaffoldState,
    ) {

        when (val currentState = viewModel.state.observeAsState().value) {

            is CreateNoteState.ErrorContent -> DialogError(currentState.message)

            is CreateNoteState.ErrorTitle -> DialogError(currentState.message)

            is CreateNoteState.Success -> navController.navigateUp()

            null -> {}
        }

        var title by remember {
            mutableStateOf("")
        }

        var content by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Titulo de la nota") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text(text = "Contenido") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 40.dp))


            Button(onClick = {
                viewModel.createNote(
                    NoteEntity(
                        title,
                        content
                    )
                )
            }) {
                Text(
                    text = "Agregar",
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 60.dp,
                        end = 60.dp
                    ),
                )
            }
        }
    }
}

@Composable
fun DialogError(message: String) {
    val showDialog = remember { mutableStateOf(true) }

    if (showDialog.value) {
        AlertDialog(onDismissRequest = {
            showDialog.value = false
        }, title = {
            Text(text = "Atención")
        }, text = {
            Text(text = message)
        }, confirmButton = {
            TextButton(onClick = { showDialog.value = false }) {
                Text(text = "Aceptar")
            }
        })
    }
}


@Preview
@Composable
fun CreateNotePreview() {
    val navController = rememberNavController()
    CreateNoteScreen(navController)
}
