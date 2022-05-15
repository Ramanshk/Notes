package com.example.notes.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Activities.MainActivity;
import com.example.notes.Activities.UpdateNoteActivity;
import com.example.notes.Entity.Notes;
import com.example.notes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @NonNull
    @Override
    public notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewholder holder, int position) {
        Notes note = notes.get(position);
        holder.title.setText(note.title);
        holder.date.setText(note.date);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);
            intent.putExtra("id", note.id);
            intent.putExtra("title", note.title);
            intent.putExtra("note", note.note);

            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewholder extends RecyclerView.ViewHolder {

        TextView title, date;
        public notesViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.updateNoteTitle);
            date = itemView.findViewById(R.id.noteDate);
        }
    }
}
