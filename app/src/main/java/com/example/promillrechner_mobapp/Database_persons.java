package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

public class Database_persons extends AppCompatActivity {

    private data_person_dao dao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterListChoose adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database__persons);

        recyclerView = findViewById(R.id.recyclerviewPerson);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterListChoose(this);
        recyclerView.setAdapter(adapter);

        dao = data_person_room_database.getDatabase(this).person_dao();
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadePersonTask().execute();
    }

    class LadePersonTask extends AsyncTask<Void, Void, List<data_person>> {

        @Override
        protected List<data_person> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<data_person> persons){
            super.onPostExecute(persons);
            adapter.setPersons(persons);
        }
    }
}
