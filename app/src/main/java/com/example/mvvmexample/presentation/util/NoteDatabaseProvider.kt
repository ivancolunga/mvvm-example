package com.example.mvvmexample.presentation.util

import android.content.Context
import androidx.room.Room
import com.example.mvvmexample.data.db.NotesDatabase

object NoteDatabaseProvider {

    fun provideNoteDatabase(context: Context): NotesDatabase = Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        NotesDatabase.DATABASE_NAME
    ).build()

}