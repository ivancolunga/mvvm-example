package com.example.mvvmexample.presentation.notes

import com.example.mvvmexample.data.model.NoteEntity

sealed class NotesState {
    data class Success(val notes: List<NoteEntity>) : NotesState()
    object EmptyState : NotesState()
}