package com.example.notesappmvvm.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NotesViewModel;
import com.example.notesappmvvm.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding updateNotesBinding;
    String priority = "1", updateTitle, updateSubTitle, updateNotes, updatePriority;
    private NotesViewModel notesViewModel;
    int updateId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateNotesBinding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(updateNotesBinding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        updateId = getIntent().getIntExtra("id", 0);
        updateTitle = getIntent().getStringExtra("title");
        updateSubTitle = getIntent().getStringExtra("subTitle");
        updateNotes = getIntent().getStringExtra("notes");
        updatePriority = getIntent().getStringExtra("priority");

        updateNotesBinding.updateNotesTitle.setText(updateTitle);
        updateNotesBinding.updateNotesSubTitle.setText(updateSubTitle);
        updateNotesBinding.updateNotes.setText(updateNotes);


        if (updatePriority.equals("1")) {
            updateNotesBinding.greenPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (updatePriority.equals("2")) {
            updateNotesBinding.yellowPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (updatePriority.equals("3")) {
            updateNotesBinding.redPriority.setBackgroundResource(R.drawable.red_shape);
        }


        //listener for green
        updateNotesBinding.greenPriority.setOnClickListener(view -> {

            updateNotesBinding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateNotesBinding.redPriority.setImageResource(0);
            updateNotesBinding.yellowPriority.setImageResource(0);

            priority = "1";

        });
        //listener for yellow
        updateNotesBinding.yellowPriority.setOnClickListener(view -> {
            updateNotesBinding.greenPriority.setImageResource(0);
            updateNotesBinding.redPriority.setImageResource(0);
            updateNotesBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);

            priority = "2";

        });
        //listener for red
        updateNotesBinding.redPriority.setOnClickListener(view -> {

            updateNotesBinding.greenPriority.setImageResource(0);
            updateNotesBinding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            updateNotesBinding.yellowPriority.setImageResource(0);


            priority = "3";

        });

        updateNotesBinding.updateNotesBtn.setOnClickListener(view -> {
            String title = updateNotesBinding.updateNotesTitle.getText().toString();
            String subTitle = updateNotesBinding.updateNotesSubTitle.getText().toString();
            String notes = updateNotesBinding.updateNotes.getText().toString();

            updateNotes(title, subTitle, notes);

        });
    }

    private void updateNotes(String title, String subTitle, String notes) {
        // get date
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM,d,yyyy", date.getTime());

        Notes updateNotesData = new Notes();

        updateNotesData.id = updateId;
        updateNotesData.notesTitle = title;
        updateNotesData.notesSubtitle = subTitle;
        updateNotesData.notes = notes;
        updateNotesData.notesPriority = priority;
        updateNotesData.notesDate = sequence.toString();

        notesViewModel.updateViewModelNotes(updateNotesData);
        Toast.makeText(this, "Notes updated Successfully!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteNotes) {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,
                    R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotesActivity.this).
                    inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.bottomSheet));
            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_Yes);
            no = view.findViewById(R.id.delete_No);

            yes.setOnClickListener(view1 -> {
                notesViewModel.deleteViewModelNotes(updateId);
                finish();

            });
            no.setOnClickListener(view1 -> {
                sheetDialog.dismiss();
            });
            sheetDialog.show();

        }

        return true;
    }
}