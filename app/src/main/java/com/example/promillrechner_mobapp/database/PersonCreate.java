package com.example.promillrechner_mobapp.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.calculator.Alcohol;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;
import com.example.promillrechner_mobapp.databaseService.Room;

import static com.example.promillrechner_mobapp.R.style.AppTheme_Picker;

public class PersonCreate extends AppCompatActivity {

    Button buttonSavePerson = null;
    Button buttonCancelPerson = null;
    NumberPicker npSize = null;
    NumberPicker npWeight = null;
    EditText editName = null;
    private PersonDao dao = null;
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
        context = getApplicationContext();

        npSize.setMinValue(150);
        npSize.setMaxValue(210);
        npSize.setValue(180);

        npWeight.setMinValue(45);
        npWeight.setMaxValue(120);
        npWeight.setValue(80);

        buttonSavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textToast = editName.getText().toString()+" wurde erfolgreich gespeichert!";

                Toast toast = Toast.makeText(context,textToast, Toast.LENGTH_SHORT);
                toast.show();

                saveWordOnClick();
                handlerGoToChooseAlcohol();
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
        RadioButton gender = findViewById(R.id.radioButtonMale);
        new SpeichernTask().execute(new Person(name.getText().toString(), npWeight.getValue(), npSize.getValue(), false));
    }

}
