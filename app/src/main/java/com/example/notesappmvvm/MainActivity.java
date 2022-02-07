package com.example.notesappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.notesappmvvm.Activity.InsertNotesActivity;
import com.example.notesappmvvm.Adapter.NotesAdapter;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNotesButton=findViewById(R.id.newNotesBtn);
        notesRecyclerView=findViewById(R.id.notesRecyclerview);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            notesAdapter=new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(notesAdapter);

        });
    }
}