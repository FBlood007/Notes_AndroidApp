package com.example.notes.repo

import androidx.lifecycle.LiveData;

import com.example.notes.data.Note;
import com.example.notes.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note :Note){
        noteDao.insert(note)
    }

    suspend fun delete(note : Note){
        noteDao.delete(note)
    }





}