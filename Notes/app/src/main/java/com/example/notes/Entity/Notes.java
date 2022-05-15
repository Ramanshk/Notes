package com.example.notes.Entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
//import androidx.room.Notes;
//import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "Notes")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String title;

    @ColumnInfo(name = "notes_date")
    public String date;

    @ColumnInfo(name = "note")
    public String note;
}
