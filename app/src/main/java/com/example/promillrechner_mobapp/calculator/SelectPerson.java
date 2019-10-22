package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.database.PersonCreate;
import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterListChoose;
import com.example.promillrechner_mobapp.database.PersonEdit;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.List;

public class SelectPerson extends AppCompatActivity {

    Button buttonGoToCreatePerson;
    Button buttonGoToAlcohol;
    private PersonDao dao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterListChoose adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_person);

        buttonGoToCreatePerson = findViewById(R.id.buttonGoToCreatePerson);
        buttonGoToAlcohol = findViewById(R.id.buttonGoToAlcohol);
        recyclerView = findViewById(R.id.recyclerviewChoosePerson);

        adapter = new RecyclerViewAdapterListChoose(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dao = Room.getDatabase(this).person_dao();

        buttonGoToCreatePerson.setOnClickListener(view -> handlerGoToCreatePerson());
        buttonGoToAlcohol.setOnClickListener(view -> handlerGoToAlcohol());

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

    private void handlerGoToCreatePerson() {
        Intent intent = new Intent(this, PersonCreate.class);
        startActivity(intent);
    }

    void handlerGoToAlcohol() {
        Intent intent = new Intent(this, Alcohol.class);
        startActivity(intent);
    }
}
