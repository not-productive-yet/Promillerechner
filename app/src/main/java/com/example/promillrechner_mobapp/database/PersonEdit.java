package com.example.promillrechner_mobapp.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.List;

public class PersonEdit extends AppCompatActivity {

    Button buttonSavePerson = null;
    Button buttonCancelPerson = null;
    NumberPicker npSize = null;
    NumberPicker npWeight = null;
    EditText editName = null;
    RadioButton radioButton = null;
    RadioGroup gender= null;
    Intent i;
    String name;
    Context context;

    private PersonDao dao;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_create);

        buttonSavePerson = findViewById(R.id.buttonSavePerson);
        buttonCancelPerson = findViewById(R.id.buttonCancelPerson);
        npSize = findViewById(R.id.numberPickerSize);
        npWeight = findViewById(R.id.numberPickerGewicht);
        editName = findViewById(R.id.editName);
        gender = findViewById(R.id.radioGroupEditGender);
        context = getApplicationContext();

        buttonSavePerson.setText("Aktualisieren");

        dao = Room.getDatabase(this).person_dao();

        i = getIntent();
        editName.setText(name);
        person = (Person) i.getSerializableExtra("person");

        editName.setText(person.getName());

        npSize.setMinValue(150);
        npSize.setMaxValue(210);
        npSize.setValue(person.getSize());

        npWeight.setMinValue(45);
        npWeight.setMaxValue(120);
        npWeight.setValue(person.getWeight());
        //if (person.isMale())
        //    radioButton.setChecked(true);
        //else
        //    radioButton.setChecked(true);

        buttonSavePerson.setOnClickListener(v -> {

            String textToast = "Die Änderungen von "+ editName.getText().toString() +" wurden erfolgreich gespeichert";

            Toast toast = Toast.makeText(context,textToast, Toast.LENGTH_SHORT);
            toast.show();

            saveWordOnClick();
            finish();
        });

        buttonCancelPerson.setOnClickListener(v -> finish());}

    class SpeichernTask extends AsyncTask<Person, Void , Void> {

        @Override
        protected Void doInBackground(Person... People) {
            dao.insert(People[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }

    private void saveWordOnClick(){
        person.setName(editName.getText().toString());
        person.setSize(npSize.getValue());
        person.setWeight(npWeight.getValue());
        person.setMale(false);

        new SpeichernTask().execute(person);
    }
}
