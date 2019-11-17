package com.example.promillrechner_mobapp.databaseService;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class Room extends RoomDatabase {

    public abstract PersonDao person_dao();

    private static Room INSTANCE;

    public static Room getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                    Room.class, "promille_database")
                    .build();
        }
        return INSTANCE;
    }
}
