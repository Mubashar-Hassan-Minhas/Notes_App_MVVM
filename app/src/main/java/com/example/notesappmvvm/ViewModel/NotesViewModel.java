package com.example.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> highToLow;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes;
        lowToHigh = notesRepository.lowToHigh;
        highToLow = notesRepository.highToLow;


    }

    public void insertViewModelNotes(Notes notes) {
        notesRepository.insertNotes(notes);
    }

    public void updateViewModelNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }

    public void deleteViewModelNotes(int id) {
        notesRepository.deleteNotes(id);
    }
}
