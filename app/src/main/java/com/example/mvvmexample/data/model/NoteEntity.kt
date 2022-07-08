package com.example.mvvmexample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,
    @PrimaryKey val id: Int? = null
)