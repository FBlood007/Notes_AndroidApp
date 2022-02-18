package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import kotlin.jvm.Volatile;

@Database(entities = [Note::class], version =2, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{

//        val migration_1_2= object: Migration(1:2){
//            override fun migrate(database, SupportSQLiteDatabase){
//                database.execSQL("Alter TABLE note_table ADD COLUMN salary")
//            }
//        }

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "note_db"
                    ).build()
                    INSTANCE= instance
                    instance


            }
        }
    }

}