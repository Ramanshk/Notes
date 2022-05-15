package com.example.notes.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.DAO;
import com.example.notes.Database.Database;
import com.example.notes.Entity.Notes;

import java.util.List;

public class NotesRepository {

    public DAO notesDao;
    public LiveData<List<Notes>> getAllNotes;


    public NotesRepository(Application application){
        Database database = Database.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
