package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.MainActivity;
import com.example.promillrechner_mobapp.R;
import com.example.promillrechner_mobapp.RecyclerViewAdapterListChoose;
import com.example.promillrechner_mobapp.databaseService.Person;
import com.example.promillrechner_mobapp.databaseService.PersonDao;

import java.util.Collections;
import java.util.List;

public class Results extends AppCompatActivity {

    Button buttonGoToShowDiagram;
    Button buttonGoBackToMain;
    TextView textPromille;
    TextView textResultInfo;
    TextView textEingabeWerte;

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
        textEingabeWerte = findViewById(R.id.textEingabeWerte);

        Intent intent = getIntent();

        double result = calculatePromille(intent);
        textPromille.setText(result + "‰");

        textEingabeWerte.setText(showEingabeWerte(intent));
        textResultInfo.setText(setResultText(result));
        textPromille.setTextColor(setTextColor(result));



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

        int countBeer300 = intent.getIntExtra("counterBeer300", 0);
        int countBeer500 = intent.getIntExtra("counterBeer500", 0);
        int countLiq300 = intent.getIntExtra("counterWine300", 0);
        int countLiq500 = intent.getIntExtra("counterWine500", 0);
        int countWine300 = intent.getIntExtra("counterLiq300", 0);
        int countWine500 = intent.getIntExtra("counterLiq500", 0);


        double alc = (alcBeer300*countBeer300 + alcBeer500*countBeer500 + alcWine300*countWine300 + alcWine500*countWine500 + alcLiq300*countLiq300 + alcLiq500*countLiq500);

        double liquid;

        if(male){
            liquid = 0.7;
        }
        else
            liquid = 0.6;
        double res = (alc/(weight*liquid));

        //rundet auf 2 nachkommastellen
        return Math.round(res*100)/100.0;
    }


    private String setResultText (double result) {

        if(result== 0)
            return getString(R.string.promilleText0);
        else if(result < 0.5)
            return getString(R.string.promilleText1);
        else if(0.5 < result && result <0.8)
            return getString(R.string.promilleText2);
        else if(0.8 < result && result <1)
            return getString(R.string.promilleText3);
        else if(1 < result && result <2)
            return getString(R.string.promilleText4);
        else if(2 < result && result <3)
            return getString(R.string.promilleText5);
        else
            return getString(R.string.promilleText6);
    }

    private int setTextColor (double result){

        if(result<0.5)
            return Color.GREEN;
        else if(result>=0.5 && result<1.5)
            return Color.YELLOW;
        else
            return Color.RED;

    }

    private String showEingabeWerte (Intent intent) {

        String eingabeWerte = RecyclerViewAdapterListChoose.current.getText().toString() + ": ";

        int countBeer300 = intent.getIntExtra("counterBeer300", 0);
        int countBeer500 = intent.getIntExtra("counterBeer500", 0);
        int countLiq300 = intent.getIntExtra("counterWine300", 0);
        int countLiq500 = intent.getIntExtra("counterWine500", 0);
        int countWine300 = intent.getIntExtra("counterLiq300", 0);
        int countWine500 = intent.getIntExtra("counterLiq500", 0);

        if(countBeer300 != 0)
            eingabeWerte = eingabeWerte + " " + countBeer300 + "x 300ml Bier";

        if(countBeer500 != 0)
            eingabeWerte = eingabeWerte + " " + countBeer500 + "x 500ml Bier";

        if(countLiq300 != 0)
            eingabeWerte = eingabeWerte + " " + countLiq300 +  "x 300ml Longdrink";

        if(countLiq500 != 0)
            eingabeWerte = eingabeWerte + " " + countLiq500 + "x 500ml Longdrink";

        if(countWine300 != 0)
            eingabeWerte = eingabeWerte + " " + countWine300 + "x 300ml Wein";

        if(countWine500 != 0)
            eingabeWerte = eingabeWerte + " " + countWine500 + "x 500ml Wein";

        return eingabeWerte;

    }
}

