package com.example.promillrechner_mobapp.databaseService;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Person name);

    @Query("SELECT * from Person")
    public List<Person> getAll();

    @Delete
    public void delete(Person name);
}
