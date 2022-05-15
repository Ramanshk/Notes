package com.example.notes.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.Dao.DAO;
import com.example.notes.Entity.Notes;

@androidx.room.Database(entities = {Notes.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract DAO notesDao();

    public static Database INSTANCE;

    public static Database getDatabaseInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "Notes").allowMainThreadQueries().build();
        }
        return INSTANCE;

    }
}
