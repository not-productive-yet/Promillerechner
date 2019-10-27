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
    TextView textResultInfo;

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
        textResultInfo = findViewById(R.id.textResultInfo);

        Intent intent = getIntent();

        double result = calculatePromille(intent);
        textPromille.setText(Double.toString(result));

        textResultInfo.setText(setResultText(result));

        buttonGoToShowDiagram.setOnClickListener(view -> handlerGoToDiagram(result));
        buttonGoBackToMain.setOnClickListener(view -> handlerGoToMain());


    }

    private void handlerGoToDiagram(double result) {
        Intent intent = new Intent(this, Diagram.class);
        intent.putExtra("result", result);
        startActivity(intent);

        //Fade right
        overridePendingTransition(R.xml.enter, R.xml.exit);
    }

    private void handlerGoToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private double calculatePromille(Intent intent){

        double weight = intent.getDoubleExtra("weight" , 60.0);
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

    private String setResultText (double result) {

        if(result < 0.3)
            return "0.3 result text";
        else if(0.3 < result && result <0.8)
            return "0.3 bis 0.8 text";
        else if(0.8 < result && result <1)
            return "0.8 bis 1text";
        else if(1 < result && result <2)
            return "1 bis 2 text";
        else if(2 < result && result <3)
            return "2 bis 3 text";
        else
            return "3 bis tod text";
    }
}

