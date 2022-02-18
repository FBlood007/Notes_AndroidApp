package com.example.notes.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(@ColumnInfo(name = "text_value")val text: String,
                @PrimaryKey(autoGenerate = true) val id: Int=0)