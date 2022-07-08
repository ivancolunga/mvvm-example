package com.example.mvvmexample.presentation.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.data.model.NoteEntity
import com.example.mvvmexample.domain.NoteRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class NotesViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state: MutableLiveData<NotesState> = MutableLiveData()

    val state: LiveData<NotesState> = _state

    fun getNotes() {
        noteRepository.getNotes().map { notes ->
            _state.value = if (notes.isEmpty()) {
                NotesState.EmptyState
            } else {
                NotesState.Success(notes)
            }
        }.launchIn(viewModelScope)
    }

}