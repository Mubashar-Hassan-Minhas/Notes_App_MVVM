package com.example.notesappmvvm.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappmvvm.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getAllNotes();


    @Insert
   public void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database  WHERE id=:id")
    public void deleteNotes(int id);

    @Update
    public  void updateNotes(Notes notes);
}
