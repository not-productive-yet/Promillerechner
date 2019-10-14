package com.example.promillrechner_mobapp.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.calculator.Alcohol;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

public class PersonCreate extends AppCompatActivity {

    Button buttonSavePerson = null;
    Button buttonCancelPerson = null;
    private PersonDao dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_person_create);

        dao = Room.getDatabase(this).person_dao();

        buttonSavePerson = findViewById(R.id.buttonSavePerson);
        buttonCancelPerson = findViewById(R.id.buttonCancelPerson);

        buttonSavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWordOnClick();
                handlerGoToChooseAlcohol();
                //finish();
            }
        });
        buttonCancelPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void handlerGoToChooseAlcohol() {
        Intent intent = new Intent(this, Alcohol.class);
        startActivity(intent);
        overridePendingTransition(R.xml.enter, R.xml.exit);
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
        EditText name = findViewById(R.id.editName);
        EditText weight = findViewById(R.id.editWeight);
        EditText size = findViewById(R.id.editSize);
        RadioButton gender = findViewById(R.id.radioButtonMale);
        new SpeichernTask().execute(new Person(name.getText().toString(), Double.parseDouble(weight.getText().toString()), Integer.parseInt(size.getText().toString()), false));
    }
}
