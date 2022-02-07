package com.example.notesappmvvm.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.example.notesappmvvm.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding activityInsertNotesBinding;
    String title, subTitle, notes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInsertNotesBinding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(activityInsertNotesBinding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        //listener for green
        activityInsertNotesBinding.greenPriority.setOnClickListener(view -> {

            activityInsertNotesBinding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            activityInsertNotesBinding.redPriority.setImageResource(0);
            activityInsertNotesBinding.yellowPriority.setImageResource(0);

            priority = "1";

        });
        //listener for yellow
        activityInsertNotesBinding.yellowPriority.setOnClickListener(view -> {
            activityInsertNotesBinding.greenPriority.setImageResource(0);
            activityInsertNotesBinding.redPriority.setImageResource(0);
            activityInsertNotesBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);

            priority = "2";

        });
        //listener for red
        activityInsertNotesBinding.redPriority.setOnClickListener(view -> {

            activityInsertNotesBinding.greenPriority.setImageResource(0);
            activityInsertNotesBinding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            activityInsertNotesBinding.yellowPriority.setImageResource(0);


            priority = "3";

        });


        //listener for insert button
        activityInsertNotesBinding.insertNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = activityInsertNotesBinding.notesTitle.getText().toString();
                subTitle = activityInsertNotesBinding.notesSubtitle.getText().toString();
                notes = activityInsertNotesBinding.notesData.getText().toString();

                createNotes(title, subTitle, notes);

            }
        });
    }

    private void createNotes(String title, String subTitle, String notes) {
        // get date
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM,d,yyyy", date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subTitle;
        notes1.notes = notes;
        notes1.notesPriority=priority;
        notes1.notesDate = sequence.toString();

        notesViewModel.insertViewModelNotes(notes1);
        Toast.makeText(this, "Notes created Successfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}