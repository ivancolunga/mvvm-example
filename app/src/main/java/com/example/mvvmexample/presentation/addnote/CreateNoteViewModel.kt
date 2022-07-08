package com.example.mvvmexample.presentation.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.data.model.NoteEntity
import com.example.mvvmexample.domain.NoteRepository
import kotlinx.coroutines.launch

class CreateNoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state: MutableLiveData<CreateNoteState> = MutableLiveData()
    val state: LiveData<CreateNoteState> = _state

    fun createNote(note: NoteEntity) {
        viewModelScope.launch {
            when {
                note.title.isEmpty() -> {
                    _state.postValue(CreateNoteState.ErrorTitle("Ingresa un título."))
                }
                note.content.isEmpty() -> {
                    _state.postValue(CreateNoteState.ErrorContent("Ingresa un contenido."))
                }
                else -> {
                    noteRepository.insertNote(note)
                    _state.postValue(CreateNoteState.Success)
                }
            }
        }
    }

}