package com.example.promillrechner_mobapp.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.databaseService.Room;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;

import java.util.List;

public class PersonList extends AppCompatActivity {


    private Button buttonBack;
    private Button buttonGoToCreate;
    private PersonDao dao;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_list);

        buttonBack = findViewById(R.id.backButtonList);
        buttonGoToCreate = findViewById(R.id.buttonGoToCreate);
        dao = Room.getDatabase(this).person_dao();

        recyclerView = findViewById(R.id.recyclerviewPerson);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterList(this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapterList.OnItemClickListener() {
            @Override
            public void onItemClick(Person position) {
                Intent intent = new Intent(PersonList.this, PersonEdit.class);
                intent.putExtra("person", position);
                startActivity(intent);

                //Fade right
                overridePendingTransition(R.xml.enter, R.xml.exit);
            }

            @Override
            public void onDeleteClick(Person position) {
                new DeletePersonTask().execute(position);
            }
        });

        buttonBack.setOnClickListener(v -> finish());

        buttonGoToCreate.setOnClickListener(v -> {
            Intent intent = new Intent(this, PersonCreate.class);
            startActivity(intent);
        });

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

    public class DeletePersonTask extends AsyncTask<Person, Void, List<Person>> {

        @Override
        protected List<Person> doInBackground(Person... people) {
            dao.delete(people[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Person> persons){
            super.onPostExecute(persons);
            adapter.setPersons(persons);
        }
    }
}
