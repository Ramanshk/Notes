package com.example.notes.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.notes.Entity.Notes;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityUpdateNoteBinding;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String stitle, snote;
    NotesViewModel notesViewModel;
    int iid;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        snote = getIntent().getStringExtra("note");

        binding.updateNoteTitle.setText(stitle);
        binding.updateNoteText.setText(snote);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.updateBtn.setOnClickListener(v -> {
            String title = binding.updateNoteTitle.getText().toString();
            String note = binding.updateNoteText.getText().toString();

            UpdateNotes(title,note);
        });

        binding.deleteBtn.setOnClickListener(v -> {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            notesViewModel.deleteNote(iid);
                            Toast.makeText(UpdateNoteActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                            finish();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete this note?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        });
    }

    private void UpdateNotes(String title, String note) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNote = new Notes();
        updateNote.id = iid;
        updateNote.title = title;
        updateNote.note = note;
        updateNote.date = sequence.toString();

        notesViewModel.updateNote(updateNote);

        Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();

        finish();
    }
}