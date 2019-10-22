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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterList;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import java.util.List;

public class PersonEdit extends AppCompatActivity {

    EditText textName;
    EditText textWeight;
    TextView textSize;
    Button buttonCancel;
    Button buttonSave;
    RadioButton male;
    RadioButton female;
    Intent i;
    String name;
    Context context;

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
        context = getApplicationContext();

        dao = Room.getDatabase(this).person_dao();

        i = getIntent();
        textName.setText(name);
        person = (Person) i.getSerializableExtra("person");

        textName.setText(person.getName());
        textSize.setText(Integer.toString(person.getSize()));
        textWeight.setText(Integer.toString(person.getWeight()));

        if (person.isMale())
            male.setChecked(true);
        else
            female.setChecked(true);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textToast = "Die Änderungen von"+ textName.getText().toString() +"wurden erfolgreich gespeichert";

                Toast toast = Toast.makeText(context,textToast, Toast.LENGTH_SHORT);
                toast.show();

                saveWordOnClick();
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

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
        EditText name = findViewById(R.id.editEditName);
        EditText size = findViewById(R.id.editEditSize);
        EditText weight = findViewById(R.id.editEditWeight);
        person.setName(name.getText().toString());
        person.setSize(Integer.parseInt(size.getText().toString()));
        person.setWeight(Integer.parseInt(weight.getText().toString()));

        new SpeichernTask().execute(person);
    }
}
