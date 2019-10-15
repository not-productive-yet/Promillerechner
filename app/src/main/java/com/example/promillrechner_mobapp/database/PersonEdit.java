package com.example.promillrechner_mobapp.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.List;

public class PersonEdit extends AppCompatActivity {

    TextView textName;
    TextView textWeight;
    TextView textSize;
    Button buttonCancel;
    Button buttonSave;
    RadioButton male;
    RadioButton female;
    Intent i;
    String name;

    private PersonDao dao;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_edit);

        textName = findViewById(R.id.editEditName);
        textSize = findViewById(R.id.editEditSize);
        textWeight = findViewById(R.id.editEditWeight);
        buttonCancel = findViewById(R.id.buttonEditCancelPerson);
        buttonSave = findViewById(R.id.buttonEditSavePerson);
        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);

        dao = Room.getDatabase(this).person_dao();

        i = getIntent();
        textName.setText(name);
        person = (Person) i.getSerializableExtra("person");

        textName.setText(person.getName());
        textSize.setText(Integer.toString(person.getSize()));
        textWeight.setText(Double.toString(person.getWeight()));

        if (person.isMale())
            male.setChecked(true);
        else
            female.setChecked(true);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //TODO: Update Database

    }
}
