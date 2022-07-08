package com.example.mvvmexample.domain

import com.example.mvvmexample.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun insertNote(note: NoteEntity)
}