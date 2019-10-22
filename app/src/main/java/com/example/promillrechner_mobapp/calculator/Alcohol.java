package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.R;

public class Alcohol extends AppCompatActivity {

    Button buttonGoToShowPromille;
    Button plusButtonBeer300;
    Button minusButtonBeer300;
    TextView counterBeer300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_alcohol);

        buttonGoToShowPromille = findViewById(R.id.buttonShowResults);

        counterBeer300 = findViewById(R.id.counterBeer300);
        minusButtonBeer300 = findViewById(R.id.minusButtonBeer300);
        plusButtonBeer300 = findViewById(R.id.plusButtonBeer300);

        plusButtonBeer300.setOnClickListener(view -> handleClickBeer300(view));
        minusButtonBeer300.setOnClickListener(view -> handleClickBeer300(view));

        buttonGoToShowPromille.setOnClickListener(view -> handlerGoToShowPromille());

    }

    private void handlerGoToShowPromille() {
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);

        //Fade right
        overridePendingTransition(R.xml.enter, R.xml.exit);
    }

    //handleClicks getten nur den Text, handleCounter macht den Rest
    private void handleClickBeer300(View view){
        String countValue = counterBeer300.getText().toString();
        handleCounter(view, countValue, counterBeer300);
    }

    //ein handleCounter für alle
    private void handleCounter(View view, String countValue, TextView tv) {

        String count;

        switch(view.getId()) {
            case R.id.minusButtonBeer300:
                if ( Integer.parseInt(countValue) <= 0)
                    return;
                count="" + (Integer.parseInt(countValue)-1);
                tv.setText(count);
                break;
            case R.id.plusButtonBeer300:
                count= "" +(Integer.parseInt(countValue)+1);
                tv.setText(count);
                break;

        }
    }
}
