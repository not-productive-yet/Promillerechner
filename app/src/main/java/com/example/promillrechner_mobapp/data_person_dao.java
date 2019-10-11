package com.example.promillrechner_mobapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface data_person_dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(data_person name);

    @Query("SELECT * from data_person")
    public List<data_person> getAll();

    @Delete
    public void delete(data_person name);
}
