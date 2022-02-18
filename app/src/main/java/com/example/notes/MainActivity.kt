package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.ViewModel.NoteVMFactory
import com.example.notes.ViewModel.NoteViewModel
import com.example.notes.data.Note
import com.example.notes.data.NoteDatabase
import com.example.notes.repo.NoteRepository
import com.example.notes.view.NotesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var noteInput: EditText
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteInput= findViewById(R.id.input)
        recyclerView= findViewById(R.id.recyclerView)

        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recyclerView.adapter=adapter



        val dao=NoteDatabase.getDatabase(applicationContext).getNoteDao()
        val repository= NoteRepository(dao)
        notesViewModel= ViewModelProvider(this, NoteVMFactory(repository)).get(NoteViewModel::class.java)
        notesViewModel.allNotes.observe(this, Observer {
            adapter.updateNotes(it)
        })

    }

    fun submitData(view: android.view.View) {
        val noteText= noteInput.text.toString()
        if(noteText.isNotEmpty()){
            notesViewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Data inserted", Toast.LENGTH_SHORT).show()
            noteInput.text.clear()
        }
    }

    fun onItemClicked(note: Note){
        notesViewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} deleted", Toast.LENGTH_SHORT).show()
    }
}