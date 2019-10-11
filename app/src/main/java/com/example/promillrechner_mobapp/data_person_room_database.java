package com.example.promillrechner_mobapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {data_person.class}, version = 1, exportSchema = false)
public abstract class data_person_room_database extends RoomDatabase {

    public abstract data_person_dao person_dao();

    private static data_person_room_database INSTANCE;

    static data_person_room_database getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    data_person_room_database.class, "alcohol_database")
                    .build();
        }
        return INSTANCE;
    }
}
