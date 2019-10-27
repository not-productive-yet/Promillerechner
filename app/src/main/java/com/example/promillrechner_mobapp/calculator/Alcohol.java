package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.R;

public class Alcohol extends AppCompatActivity {

    Button buttonGoToShowPromille;

    Button plusButtonBeer300;
    Button minusButtonBeer300;
    TextView counterBeer300;

    Button plusButtonBeer500;
    Button minusButtonBeer500;
    TextView counterBeer500;

    Button plusButtonWine300;
    Button minusButtonWine300;
    TextView counterWine300;

    Button plusButtonWine500;
    Button minusButtonWine500;
    TextView counterWine500;

    Button plusButtonLiq300;
    Button minusButtonLiq300;
    TextView counterLiq300;

    Button plusButtonLiq500;
    Button minusButtonLiq500;
    TextView counterLiq500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_alcohol);

        buttonGoToShowPromille = findViewById(R.id.buttonShowResults);

        //Beer 300
        counterBeer300 = findViewById(R.id.counterBeer300);
        counterBeer300.bringToFront();
        minusButtonBeer300 = findViewById(R.id.minusButtonBeer300);
        plusButtonBeer300 = findViewById(R.id.plusButtonBeer300);
        plusButtonBeer300.setOnClickListener(view -> handleGetText(view));
        minusButtonBeer300.setOnClickListener(view -> handleGetText(view));

        //Beer 500
        counterBeer500 = findViewById(R.id.counterBeer500);
        counterBeer500.bringToFront();
        minusButtonBeer500 = findViewById(R.id.minusButtonBeer500);
        plusButtonBeer500 = findViewById(R.id.plusButtonBeer500);
        plusButtonBeer500.setOnClickListener(view -> handleGetText(view));
        minusButtonBeer500.setOnClickListener(view -> handleGetText(view));

        //Wine 300
        counterWine300 = findViewById(R.id.counterWine300);
        counterWine300.bringToFront();
        minusButtonWine300 = findViewById(R.id.minusButtonWine300);
        plusButtonWine300 = findViewById(R.id.plusButtonWine300);
        plusButtonWine300.setOnClickListener(view -> handleGetText(view));
        minusButtonWine300.setOnClickListener(view -> handleGetText(view));

        //Wine 500
        counterWine500 = findViewById(R.id.counterWine500);
        counterWine500.bringToFront();
        minusButtonWine500 = findViewById(R.id.minusButtonWine500);
        plusButtonWine500 = findViewById(R.id.plusButtonWine500);
        plusButtonWine500.setOnClickListener(view -> handleGetText(view));
        minusButtonWine500.setOnClickListener(view -> handleGetText(view));

        //Liq 300
        counterLiq300 = findViewById(R.id.counterLiq300);
        counterLiq300.bringToFront();
        minusButtonLiq300 = findViewById(R.id.minusButtonLiq300);
        plusButtonLiq300 = findViewById(R.id.plusButtonLiq300);
        plusButtonLiq300.setOnClickListener(view -> handleGetText(view));
        minusButtonLiq300.setOnClickListener(view -> handleGetText(view));

        //Liq 50
        counterLiq500 = findViewById(R.id.counterLiq500);
        counterLiq500.bringToFront();
        minusButtonLiq500 = findViewById(R.id.minusButtonLiq500);
        plusButtonLiq500 = findViewById(R.id.plusButtonLiq500);
        plusButtonLiq500.setOnClickListener(view -> handleGetText(view));
        minusButtonLiq500.setOnClickListener(view -> handleGetText(view));

        Intent intent = new Intent();
        boolean male = intent.getBooleanExtra("male", true);
        double weight = intent.getDoubleExtra("weight", 80.0);
        buttonGoToShowPromille.setOnClickListener(view -> handlerGoToShowPromille(male, weight));

    }

    //gettet nur den Text, handleCounter macht den Rest
    private void handleGetText(View view){
        String countValue = "";
        TextView counter = null;
        switch(view.getId()) {
            case R.id.plusButtonBeer300:
            case R.id.minusButtonBeer300:
                countValue = counterBeer300.getText().toString();
                counter = counterBeer300;
                break;
            case R.id.plusButtonBeer500:
            case R.id.minusButtonBeer500:
                countValue = counterBeer500.getText().toString();
                counter = counterBeer500;
                break;
            case R.id.plusButtonWine300:
            case R.id.minusButtonWine300:
                countValue = counterWine300.getText().toString();
                counter = counterWine300;
                break;
            case R.id.plusButtonWine500:
            case R.id.minusButtonWine500:
                countValue = counterWine500.getText().toString();
                counter = counterWine500;
                break;
            case R.id.plusButtonLiq300:
            case R.id.minusButtonLiq300:
                countValue = counterLiq300.getText().toString();
                counter = counterLiq300;
                break;
            case R.id.plusButtonLiq500:
            case R.id.minusButtonLiq500:
                countValue = counterLiq500.getText().toString();
                counter = counterLiq500;
                break;
        }

        handleCounter(view, countValue, counter);
    }

    //ein handleCounter für alle
    private void handleCounter(View view, String countValue, TextView tv) {

        String count;

        switch(view.getId()) {
            case R.id.minusButtonBeer300:
            case R.id.minusButtonBeer500:
            case R.id.minusButtonWine300:
            case R.id.minusButtonWine500:
            case R.id.minusButtonLiq300:
            case R.id.minusButtonLiq500:

                if ( Integer.parseInt(countValue) == 0)
                    return;
                count="" + (Integer.parseInt(countValue)-1);
                tv.setText(count);
                break;

            case R.id.plusButtonBeer300:
            case R.id.plusButtonBeer500:
            case R.id.plusButtonWine300:
            case R.id.plusButtonWine500:
            case R.id.plusButtonLiq300:
            case R.id.plusButtonLiq500:

                count= "" +(Integer.parseInt(countValue)+1);
                tv.setText(count);
                break;
        }
    }

    private void handlerGoToShowPromille(boolean male, double weight) {
        Intent intent = new Intent(this, Results.class);

        int beer300 = Integer.valueOf(counterBeer300.getText().toString());
        int beer500 = Integer.valueOf(counterBeer500.getText().toString());
        int wine300 = Integer.valueOf(counterWine300.getText().toString());
        int wine500 = Integer.valueOf(counterWine500.getText().toString());
        int liq300 = Integer.valueOf(counterLiq300.getText().toString());
        int liq500 = Integer.valueOf(counterLiq500.getText().toString());

        intent.putExtra("counterBeer300", beer300);
        intent.putExtra("counterBeer500", beer500);
        intent.putExtra("counterWine300", wine300);
        intent.putExtra("counterWine500", wine500);
        intent.putExtra("counterLiq300", liq300);
        intent.putExtra("counterLiq500", liq500);

        intent.putExtra("male", male);
        intent.putExtra("weight", weight);

        startActivity(intent);

        //Fade right
        overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}
