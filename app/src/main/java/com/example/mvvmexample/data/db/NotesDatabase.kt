package com.example.mvvmexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmexample.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}