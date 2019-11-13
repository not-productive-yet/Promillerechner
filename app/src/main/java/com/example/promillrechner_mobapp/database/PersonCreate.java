package com.example.promillrechner_mobapp.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.calculator.Alcohol;
import com.example.promillrechner_mobapp.calculator.SelectPerson;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;


public class PersonCreate extends AppCompatActivity {

    Button buttonSavePerson = null;
    Button buttonCancelPerson = null;
    NumberPicker npSize = null;
    NumberPicker npWeight = null;
    EditText editName = null;
    RadioButton male = null;
    RadioButton female = null;
    boolean genderMale = true;
    PersonDao dao = null;
    Context context = null;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_create);

        dao = Room.getDatabase(this).person_dao();

        buttonSavePerson = findViewById(R.id.buttonSavePerson);
        buttonCancelPerson = findViewById(R.id.buttonCancelPerson);
        npSize = findViewById(R.id.numberPickerSize);
        npWeight = findViewById(R.id.numberPickerGewicht);
        editName = findViewById(R.id.editName);
        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);
        context = getApplicationContext();

        buttonSavePerson.setText("Sichern");

        npSize.setMinValue(150);
        npSize.setMaxValue(210);
        npSize.setValue(180);

        npWeight.setMinValue(45);
        npWeight.setMaxValue(120);
        npWeight.setValue(80);

        male.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (male.isChecked()) {
                genderMale = true;
            }
        });

        female.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(female.isChecked()) {
                genderMale = false;
            }
        });

        buttonSavePerson.setOnClickListener(v -> {

            if(editName.getText().toString().matches("")){
                String textToast = "Bitte geben Sie einen Namen ein";

                Toast toast = Toast.makeText(context,textToast, Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                String textToast = editName.getText().toString()+" wurde erfolgreich gespeichert!";

                Toast toast = Toast.makeText(context,textToast, Toast.LENGTH_SHORT);
                toast.show();

                saveWordOnClick();
                finish();
            }
        });

        buttonCancelPerson.setOnClickListener(v -> finish());
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
        new SpeichernTask().execute(new Person(editName.getText().toString(), npWeight.getValue(), npSize.getValue(), genderMale));
    }

}
