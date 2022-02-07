package com.example.notesappmvvm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappmvvm.Activity.UpdateNotesActivity;
import com.example.notesappmvvm.MainActivity;
import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes notesData = notes.get(position);
        if (notesData.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (notesData.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (notesData.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(notesData.notesTitle);
        holder.subTitle.setText(notesData.notesSubtitle);
        holder.notesDate.setText(notesData.notesDate);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
            intent.putExtra("id", notesData.id);
            intent.putExtra("title", notesData.notesTitle);
            intent.putExtra("subTitle", notesData.notesSubtitle);
            intent.putExtra("priority", notesData.notesPriority);
            intent.putExtra("notes", notesData.notes);

            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle, notesDate;
        View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subTitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);

        }
    }
}
