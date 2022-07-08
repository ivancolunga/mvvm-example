package com.example.mvvmexample.data.repositoryimpl

import com.example.mvvmexample.data.db.NoteDao
import com.example.mvvmexample.data.model.NoteEntity
import com.example.mvvmexample.domain.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<NoteEntity>> = dao.getNotes()

    override suspend fun insertNote(note: NoteEntity) {
        dao.insertNote(note)
    }
}