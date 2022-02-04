package com.example.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Model.Notes;
import com.example.notesappmvvm.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public  NotesRepository notesRepository;
    public LiveData<List<Notes>>getAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

       notesRepository=new NotesRepository(application);
       getAllNotes=notesRepository.getAllNotes;


    }

    void insertViewModelNotes(Notes notes){
        notesRepository.insertNotes(notes);
    }

    void updateViewModelNotes(Notes notes){
        notesRepository.updateNotes(notes);
    }
    void deleteViewModelNotes(int id){
        notesRepository.deleteNotes(id);
    }
}
