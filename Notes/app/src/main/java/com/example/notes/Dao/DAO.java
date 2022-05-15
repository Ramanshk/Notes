package com.example.notes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.Entity.Notes;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM Notes")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
