package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Calc_choose_person extends AppCompatActivity {

    Button buttonGoToCreatePerson = null;
    private data_person_dao dao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterListChoose adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_choose_person);

        buttonGoToCreatePerson = findViewById(R.id.buttonGoToCreatePerson);


        recyclerView = findViewById(R.id.recyclerviewChoosePerson);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterListChoose(this);
        recyclerView.setAdapter(adapter);

        dao = data_person_room_database.getDatabase(this).person_dao();


        buttonGoToCreatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToCreatePerson();
            }
        });

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

    private void handlerGoToCreatePerson() {
        Intent intent = new Intent(this, Database_person_create.class);
        startActivity(intent);
    }
}
