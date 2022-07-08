package com.example.mvvmexample.presentation.addnote

sealed class CreateNoteState {
    object Success : CreateNoteState()
    data class ErrorTitle(val message: String) : CreateNoteState()
    data class ErrorContent(val message: String) : CreateNoteState()
}