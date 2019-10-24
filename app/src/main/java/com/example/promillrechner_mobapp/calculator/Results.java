package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.MainActivity;
import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;

import java.util.Collections;
import java.util.List;

public class Results extends AppCompatActivity {

    Button buttonGoToShowDiagram;
    Button buttonGoBackToMain;
    TextView textPromille;

    //alkoholgehalt in gramm
    double alcBeer300 = 12;
    double alcBeer500 = 20;
    double alcWine300= 28.8;
    double alcWine500= 48;
    double alcLiq300= 18.3;
    double alcLiq500= 30.5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_results);

        buttonGoToShowDiagram = findViewById(R.id.buttonGoToShowDiagram);
        buttonGoBackToMain = findViewById(R.id.buttonGoBackToMain);
        textPromille = findViewById(R.id.textResultPromille);

        double result = calculatePromille();
        textPromille.setText(Double.toString(result));


        buttonGoToShowDiagram.setOnClickListener(view -> handlerGoToDiagram());
        buttonGoBackToMain.setOnClickListener(view -> handlerGoToMain());

    }

    private void handlerGoToDiagram() {
        Intent intent = new Intent(this, Diagram.class);
        startActivity(intent);
    }

    private void handlerGoToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private double calculatePromille(){

        Intent intent = getIntent();
        double weight = intent.getDoubleExtra("Weight" , 60.0);
        boolean male = intent.getBooleanExtra("male", true);

        //Todo intents wirklich holen, momentan arbeitet er mit defaults
        int countBeer300 = intent.getIntExtra("countBeer300", 0);
        int countBeer500 = intent.getIntExtra("countBeer500", 0);
        int countLiq300 = intent.getIntExtra("countWine300", 0);
        int countLiq500 = intent.getIntExtra("countWine500", 0);
        int countWine300 = intent.getIntExtra("countLiq300", 0);
        int countWine500 = intent.getIntExtra("countLiq500", 0);


        double alc = (alcBeer300*countBeer300 + alcBeer500*countBeer500 + alcWine300*countWine300 + alcWine500*countWine500 + alcLiq300*countLiq300 + alcLiq500*countLiq500);

        double liquid;

        if(male){
            liquid = 0.68;
        }
        else
            liquid = 0.55;

        double res = (alc/(weight*liquid));

        //rundet auf 2 nachkommastellen
        return Math.round(res*100)/100.0;
    }
}

