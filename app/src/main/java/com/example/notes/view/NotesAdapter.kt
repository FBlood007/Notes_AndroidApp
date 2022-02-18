package com.example.notes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.data.Note

class NotesAdapter(private val context: Context, private val listener: MainActivity): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    val allnNotes= ArrayList<Note>()

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView= itemView.findViewById(R.id.noteText)
        val deleteButton: ImageView= itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allnNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       holder.textView.text= allnNotes[holder.adapterPosition].text
    }

    override fun getItemCount(): Int {
        return allnNotes.size
    }

    fun updateNotes(notesList: List<Note>){
        allnNotes.clear()
        allnNotes.addAll(notesList)

        notifyDataSetChanged()
    }
}
interface  INoteAdapter{
    fun onItemClicked(note: Note)
}