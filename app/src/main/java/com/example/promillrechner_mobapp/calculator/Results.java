package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;

import java.util.Collections;
import java.util.List;

public class Results extends AppCompatActivity {

    private PersonDao dao;
    private List<Person> persons = Collections.EMPTY_LIST;

    Button buttonGoToShowDiagram = null;
    TextView textPromille = null;

    Intent intent;
    double weight;
    boolean male;
    double alc;
    double liquid;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_results);

        intent = getIntent();

        //TODO: Daten von Person setzen und Promillewert berechnen

        buttonGoToShowDiagram = findViewById(R.id.buttonGoToShowDiagram);
        textPromille = findViewById(R.id.textPromille);
        weight = intent.getDoubleExtra("Weight" , 0.0);
        male = false;
        alc = 24.0;
        liquid = 0.0;


        if(male){
            liquid = 0.68;
        }
        else
            liquid = 0.55;


        result = alc/(weight*liquid);

        textPromille.setText(Double.toString(result));

        buttonGoToShowDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
