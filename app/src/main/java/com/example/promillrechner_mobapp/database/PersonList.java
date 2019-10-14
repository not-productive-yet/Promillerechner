package com.example.promillrechner_mobapp.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.RecyclerViewAdapterListChoose;
import com.example.promillrechner_mobapp.databaseService.Room;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;

import java.util.List;

public class PersonList extends AppCompatActivity {

    private PersonDao dao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_list);

        recyclerView = findViewById(R.id.recyclerviewPerson);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterList(this);
        recyclerView.setAdapter(adapter);

        dao = Room.getDatabase(this).person_dao();
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadePersonTask().execute();
    }

    class LadePersonTask extends AsyncTask<Void, Void, List<Person>> {

        @Override
        protected List<Person> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Person> persons){
            super.onPostExecute(persons);
            adapter.setPersons(persons);
        }
    }
}
