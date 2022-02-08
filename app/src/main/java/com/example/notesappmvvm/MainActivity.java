package com.example.notesappmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notesappmvvm.Activity.InsertNotesActivity;
import com.example.notesappmvvm.Adapter.NotesAdapter;
import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    TextView noFilter, highToLow, lowToHigh;
    List<Notes> filterNotesAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNotesButton = findViewById(R.id.newNotesBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerview);

        noFilter = findViewById(R.id.noFilter);
        highToLow = findViewById(R.id.highToLow);
        lowToHigh = findViewById(R.id.lowToHigh);

        noFilter.setBackgroundResource(R.drawable.filter_selected_shape);

        noFilter.setOnClickListener(view -> {
            loadData(0);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        highToLow.setOnClickListener(view -> {
            loadData(1);
            highToLow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        lowToHigh.setOnClickListener(view -> {
            loadData(2);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNotesAllList = notes;
            }
        });
    }

    private void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        } else if (i == 1) {
            notesViewModel.highToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        } else if (i == 2) {
            notesViewModel.lowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        }
    }

    public void setAdapter(List<Notes> notes) {
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(notesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                notesFilter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void notesFilter(String s) {
        Log.e("@@@@", "NotesFilter" + s);

        ArrayList<Notes> filterNames = new ArrayList<>();

        for (Notes notes : this.filterNotesAllList) {
            if (notes.notesTitle.contains(s) || notes.notesSubtitle.contains(s)) {
                filterNames.add(notes);
            }
        }
        this.notesAdapter.searchNotes(filterNames);
    }

}