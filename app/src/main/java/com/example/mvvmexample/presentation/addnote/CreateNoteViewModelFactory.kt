package com.example.mvvmexample.presentation.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexample.domain.NoteRepository

class CreateNoteViewModelFactory(
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor(
            NoteRepository::class.java
        ).newInstance(
            noteRepository
        )

}