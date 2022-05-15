package com.example.notes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notes.Adapter.NotesAdapter;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton add_note_btn;
    private RecyclerView all_notes;
    NotesViewModel notesViewModel;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        add_note_btn = findViewById(R.id.add_note_btn);
        all_notes = findViewById(R.id.all_notes);

        getSupportActionBar().hide();

        add_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        notesViewModel.getAllNotes.observe(this, notes -> {
            all_notes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            adapter = new NotesAdapter(MainActivity.this, notes);
            all_notes.setAdapter(adapter);
        });
    }
}