package com.example.notes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.notes.Entity.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityAddNotesBinding;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNotesBinding binding;
    String title, note;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.addNoteBtn.setOnClickListener(v -> {
            title = binding.noteTitle.getText().toString();
            note = binding.noteText.getText().toString();

            CreateNotes(title,note);
        });
    }

    private void CreateNotes(String title, String note) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes note1 = new Notes();
        note1.title = title;
        note1.note = note;
        note1.date = sequence.toString();
        notesViewModel.insertNote(note1);

        Toast.makeText(this, "Note created", Toast.LENGTH_SHORT).show();

        finish();
    }
}